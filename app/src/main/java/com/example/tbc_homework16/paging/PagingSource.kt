package com.example.tbc_homework16.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tbc_homework16.model.UserData
import com.example.tbc_homework16.network.UserApi


const val STARTING_KEY = 1

class PagingSource(val api: UserApi) : PagingSource<Int, UserData.User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData.User> {
        return try {
            val PageNumber = params.key ?: STARTING_KEY
            // Load as many items as hinted by params.loadSize
            val range = PageNumber.until(PageNumber + params.loadSize)
            val response: UserData = api.getPersonData(PageNumber)

            LoadResult.Page(
                data =   response.data,
                prevKey = when (PageNumber) {
                    STARTING_KEY -> null
                    else -> ensureValidKey(key = range.first - params.loadSize)
                },
                nextKey =  if (PageNumber < response.totalPages) PageNumber + 1 else null
            )
        }
        //if error
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    private fun ensureValidKey(key: Int) = Integer.max(STARTING_KEY, key)

    override fun getRefreshKey(state: PagingState<Int, UserData.User>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}