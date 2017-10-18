import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;

public class RestClient implements IDogClient
{
    private String baseUri;

    public String getBaseUri() {return this.baseUri;}

    public void setBaseUri(String baseUri) {this.baseUri = baseUri;}

    public void RestClient() 
    {
        com.mashape.unirest.http.Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                        = new com.fasterxml.jackson.databind.ObjectMapper();
        
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        
            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public DogInfo GetSubBreed (String breedName, String subBreedName) throws Exception
    {
        DogInfo info = new DogInfo();
        try { 
        HttpResponse<DogInfo> dogInfo = Unirest.get("https://dog.ceo/api/breed/{breed}/{subBreed}/images/random")
                                        .routeParam("breed", breedName)
                                        .routeParam("subBreed", subBreedName)
                                        .asObject(DogInfo.class);
        System.out.println(dogInfo.getBody());
        return dogInfo.getBody();
    }
    catch (Exception e){}

    
    
    return info;
}

}