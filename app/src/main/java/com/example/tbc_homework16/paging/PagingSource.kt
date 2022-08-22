package com.example.tbc_homework16.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tbc_homework16.model.UserData
import com.example.tbc_homework16.network.UserApi

class PagingSource(val api: UserApi) : PagingSource<Int, UserData.Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData.Data> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response: UserData = api.getPersonData(nextPageNumber)

            LoadResult.Page(
                 response.data,
                if (nextPageNumber > 0) nextPageNumber - 1 else null,
                if (nextPageNumber < response.totalPages) nextPageNumber + 1 else null
            )
        }
        //if error
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserData.Data>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}