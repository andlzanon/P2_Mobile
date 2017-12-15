package zanon.andl.p2.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zanon.andl.p2.entity.AcaoSocialListEntity;

/**
 * Created by Andre on 27/11/2017.
 */

/**
 * Classe responsável por inicializar o Retrofit com o GSON para a
 * tradução de JSON em Java Object
 */
public class AcaoSocialApi {

    private static AcaoSocialApi instancia;
    private AcaoSocialService acaoSocialService;

    /**
     * Construtor estatico de instancia
     * @return instancia da ListGamesAPI
     */
    public static AcaoSocialApi getInstance(){
        if(instancia == null)
            instancia = new AcaoSocialApi();

        return instancia;
    }

    /**
     * Contrutor privado responsável por criar o retrofit e
     * junta-lo ao GSON
     */
    private AcaoSocialApi(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://dl.dropboxusercontent.com/")
                .addConverterFactory(defaultConvertFactory())
                .build();

        this.acaoSocialService = retrofit.create(AcaoSocialService.class);
    }

    /**
     * Funcao que cria o Gson que sera anexado ao retrofit
     * @return Converter que sera anexado junto ao Retrofit
     */
    private Converter.Factory defaultConvertFactory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("dd-MM-yyyy'T'HH:mm:ss")
                .create();
        return GsonConverterFactory.create(gson);
    }

    /**
     * Funcao que chama o metodo GET
     * @return lista de acoes sociais da web
     */
    public Call<AcaoSocialListEntity> getAcoesSociais(){
        return acaoSocialService.getAcoesSociais();
    }
}
