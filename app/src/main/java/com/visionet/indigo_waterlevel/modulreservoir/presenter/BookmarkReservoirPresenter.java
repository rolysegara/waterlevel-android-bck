package com.visionet.indigo_waterlevel.modulreservoir.presenter;

import android.util.Log;

import com.visionet.indigo_waterlevel.baseapp.BasePresenter;
import com.visionet.indigo_waterlevel.basenetwork.NetworkCallback;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelRequestBookmarkReservoirOrClear;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelResponseBookmarkReservoirOrClear;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelResponseGetBookmarkedReservoirs;
import com.visionet.indigo_waterlevel.modulreservoir.view.BookmarkReservoirView;

public class BookmarkReservoirPresenter extends BasePresenter<BookmarkReservoirView> {

    public BookmarkReservoirPresenter(BookmarkReservoirView view) {
        super.attachView(view);
    }

    public void loadBookmarkReservoirOrClear(
            ModelRequestBookmarkReservoirOrClear modelRequestBookmarkReservoirOrClear
    ) {
        view.showLoadingBookmark();
        addSubscribe(apiStores.bookmarkReservoirOrClear(
                modelRequestBookmarkReservoirOrClear
        ), new NetworkCallback<ModelResponseBookmarkReservoirOrClear>() {
            @Override
            public void onSuccess(ModelResponseBookmarkReservoirOrClear modelResponseBookmarkReservoirOrClear) {
                view.getResponseBookmarkReservoirOrClear(modelResponseBookmarkReservoirOrClear);
                //view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                Log.d("ErrorBodyBookmark", message);
                view.hideLoadingBookmark();
                view.getResponseBookmarkReservoirOrClearFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoadingBookmark();
            }
        });
    }

    public void loadBookmarkedReservoirs(
            int userId,
            String filter,
            String sorting,
            int maxResultCount,
            int skipCount
    ) {
        view.showLoadingBookmark();
        addSubscribe(apiStores.getBookmarkedReservoirs(
                userId, filter, sorting, maxResultCount, skipCount
        ), new NetworkCallback<ModelResponseGetBookmarkedReservoirs>() {
            @Override
            public void onSuccess(ModelResponseGetBookmarkedReservoirs modelResponseGetBookmarkedReservoirs) {
                view.getResponseBookmarkedReservoirs(modelResponseGetBookmarkedReservoirs);
                //view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                Log.d("ErrorBodyBookmarked", message);
                view.hideLoadingBookmark();
                view.getResponseBookmarkReservoirOrClearFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoadingBookmark();
            }
        });
    }
}
