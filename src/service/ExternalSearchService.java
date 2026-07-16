package service;

import com.sun.net.httpserver.Request;

import java.util.List;

public interface ExternalSearchService {
    List<String> externalServisSearch(String bdIndex, Request searchRequest);
}
