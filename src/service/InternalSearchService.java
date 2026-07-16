package service;


import com.sun.net.httpserver.Request;
import dto.ResponseExternalSearchDto;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class InternalSearchService {
    private final ExternalSearchService externalSearchService;
    private final static List<String> BD_INDEX = List.of("0", "1", "2");
    private final static String NOT_FOUND = "NOT_FOUND";
    private final static String EXCEPTION_MESSAGE = "FAILED_PRECONDITION";

    public InternalSearchService(ExternalSearchService externalSearchService) {
        this.externalSearchService = externalSearchService;
    }

    public String internalSearch(Request searchRequest) {
        List<ResponseExternalSearchDto> externalSearchDtoList = BD_INDEX.stream()
            .map(index -> CompletableFuture.supplyAsync(() -> executeCall(index, searchRequest)))
            .map(CompletableFuture::join)
            .toList();
        if (externalSearchDtoList.stream()
            .allMatch(responseExternalSearchDto -> responseExternalSearchDto.isEmpty()
                                                   || NOT_FOUND.equals(responseExternalSearchDto.getExceptionMessage()))) {
            return NOT_FOUND;
        }
        Optional<String> response = externalSearchDtoList.stream()
            .filter(responseExternalSearchDto -> !responseExternalSearchDto.isEmpty() && responseExternalSearchDto.getMessage() != null)
            .map(ResponseExternalSearchDto::getMessage)
            .findFirst();
        if (response.isPresent()){
            return response.get();
        }
        throw new RuntimeException(EXCEPTION_MESSAGE);
    }

    private ResponseExternalSearchDto executeCall(String index, Request searchRequest) {
        List<String> data = null;
        Exception exception = null;
        try {
            data = externalSearchService.externalServisSearch(index, searchRequest);
        } catch (RuntimeException e) {
            exception = e;
        }
        ResponseExternalSearchDto responseExternalSearchDto = new ResponseExternalSearchDto();
        if (exception == null) {
            if (data == null || data.isEmpty()) {
                return responseExternalSearchDto;
            }
            responseExternalSearchDto.setMessage(data.getFirst());
        } else {
            responseExternalSearchDto.setExceptionMessage(exception.getMessage());
        }
        return responseExternalSearchDto;
    }

}
