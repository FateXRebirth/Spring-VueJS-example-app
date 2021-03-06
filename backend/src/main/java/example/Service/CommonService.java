package example.Service;

import example.Repository.CommonRepository;
import example.Response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    @Autowired
    CommonRepository commonRepository;

    public Result getNews() {
        return commonRepository.getNews();
    }

    public Result getBanner() {
        return commonRepository.getBanner();
    }

    public Result getSpecification() {
        return commonRepository.getSpecification();
    }
}
