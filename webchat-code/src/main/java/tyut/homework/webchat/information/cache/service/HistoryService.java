package tyut.homework.webchat.information.cache.service;

/**
 * @author TokisakiKurumi
 * @className HistoryService
 * @date 2023/7/2
 * @description
 **/
public interface HistoryService {
    boolean getHistory(String userId,int start,int end);
}
