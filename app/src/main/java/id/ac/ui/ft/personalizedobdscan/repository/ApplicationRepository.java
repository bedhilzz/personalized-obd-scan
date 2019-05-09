package id.ac.ui.ft.personalizedobdscan.repository;

public class ApplicationRepository {
    private APIService apiService;

    private static class SingletonHelper
    {
        private static final ApplicationRepository INSTANCE = new ApplicationRepository();
    }

    public static ApplicationRepository getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public ApplicationRepository() {
        apiService = RetrofitBuilder.retrofit.create(APIService.class);
    }
}

