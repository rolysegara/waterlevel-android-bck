package com.visionet.indigo_waterlevel.modulreservoir.view;

import com.visionet.indigo_waterlevel.modulreservoir.model.ModelResponseBookmarkReservoirOrClear;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelResponseGetBookmarkedReservoirs;

public interface BookmarkReservoirView {
    void showLoadingBookmark();

    void hideLoadingBookmark();

    void getResponseBookmarkReservoirOrClear(
            ModelResponseBookmarkReservoirOrClear modelResponseBookmarkReservoirOrClear);

    void getResponseBookmarkReservoirOrClearFailed(String message);

    void getResponseBookmarkedReservoirs(
            ModelResponseGetBookmarkedReservoirs modelResponseGetBookmarkedReservoirs);

    void getResponseBookmarkedReservoirsFailed(String message);
}
