//package com.jaiecom.karwaty.app_reviews.data
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.jaiecom.karwaty.core.utils.Const.PAGE_DEFAULT
//import com.jaiecom.karwaty.core.utils.Const.PAGE_SIZE
//import retrofit2.HttpException
//import java.io.IOException
//
//
//class ReviewPagingSource(
//    private val apiInterface: ReviewApiInterface,
//    private val request: Boolean
//) : PagingSource<Int, Any>() {
//
//    private var totalItem = PAGE_DEFAULT
//
//    override fun getRefreshKey(state: PagingState<Int, Any>): Int? {
//        return null
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
//        val page = params.key ?: PAGE_DEFAULT
//        return try {
//            request.offset = page
//            val response = apiInterface.getAllRequests(request)
//
//            val orders = response.body()?.data?.orders
//            totalItem = response.body()?.data?.total_orders?: PAGE_DEFAULT
//            val nextKey = if (orders.isNullOrEmpty()) {
//                null
//            } else {
//                if (
//                    params.loadSize == 3 * PAGE_SIZE
//                ) {
//                    page + 1
//                } else {
//                    page + (params.loadSize / PAGE_SIZE)
//                }
//            }
//            LoadResult.Page(
//                data = orders ?: emptyList(),
//                prevKey = if (page == PAGE_DEFAULT) null else page - PAGE_DEFAULT,
//                nextKey = nextKey
//            )
//        } catch (exception: IOException) {
//            LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            LoadResult.Error(exception)
//        }
//    }
//
//}
