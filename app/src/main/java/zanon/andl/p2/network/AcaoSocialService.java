package zanon.andl.p2.network;

import retrofit2.Call;
import retrofit2.http.GET;
import zanon.andl.p2.entity.AcaoSocialListEntity;

/**
 * Created by Andre on 27/11/2017.
 */

public interface AcaoSocialService {

    /**
     * Assinatura Retrofit para acessar a URL
     * @return lista de jogos conforme o arquivo JSON
     */
    @GET("s/50vmlj7dhfaibpj/sociais.json")
    Call<AcaoSocialListEntity> getAcoesSociais();
}
