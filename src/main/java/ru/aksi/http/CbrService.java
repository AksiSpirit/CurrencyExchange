package ru.aksi.http;

import retrofit.http.GET;
import retrofit.http.Query;
import ru.aksi.dto.ValCurs;

public interface CbrService {
    @GET("/scripts/XML_daily.asp")
    ValCurs getExchange(@Query("date_req") String date);
}
