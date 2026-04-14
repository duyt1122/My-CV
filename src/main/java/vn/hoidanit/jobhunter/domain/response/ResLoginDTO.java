package vn.hoidanit.jobhunter.domain.response;

public class ResLoginDTO {
    private String accessToken;

    public ResLoginDTO() {
    }

    public ResLoginDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
}
