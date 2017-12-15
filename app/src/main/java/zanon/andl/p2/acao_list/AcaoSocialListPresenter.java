package zanon.andl.p2.acao_list;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zanon.andl.p2.R;
import zanon.andl.p2.entity.AcaoSocialEntity;
import zanon.andl.p2.entity.AcaoSocialListEntity;
import zanon.andl.p2.network.AcaoSocialApi;

/**
 * Created by Andre on 15/12/2017.
 */

public class AcaoSocialListPresenter {

    //interface do MVP que nao deixa acessar todos os metodos
    //da view, somente os necessarios
    private AcaoSocialListView acaoSocialListView;

    /**
     * Construtor padrao
     * @param acaoSocialListView da camada de visualizacao do MVP
     */
    public AcaoSocialListPresenter(AcaoSocialListView acaoSocialListView){
        this.acaoSocialListView = acaoSocialListView;
    }

    /**
     * Funcao que acessa os dados da web
     */
    public void acessaDados(){
        acaoSocialListView.initProgressBar();
        //instancia a api
        final AcaoSocialApi listGamesApi = AcaoSocialApi.getInstance();
        //chama o metdod getGames que faz o GET na API com o arquivo JSON
        listGamesApi.getAcoesSociais().enqueue(new Callback<AcaoSocialListEntity>() {
            @Override
            public void onResponse(Call<AcaoSocialListEntity> call, Response<AcaoSocialListEntity> response) {
                AcaoSocialListEntity acaoSocialListEntity = response.body();
                //se a lista nao for vazia, ou seja, se a resposta foi bem sucedida
                if(acaoSocialListEntity != null){
                    acaoSocialListView.stopProgressBar();
                    acaoSocialListView.atualizaLista(acaoSocialListEntity.getAcaoSocialEntities());
                }
                //senao mostra mensagem de erro
                else{
                    acaoSocialListView.stopProgressBar();
                    acaoSocialListView.mensagemDeErro(acaoSocialListView.getTextFromR(R.string.erro_servidor));
                }
            }

            @Override
            public void onFailure(Call<AcaoSocialListEntity> call, Throwable t) {
                acaoSocialListView.stopProgressBar();
                acaoSocialListView.mensagemDeErro(acaoSocialListView.getTextFromR(R.string.erro_servidor));
            }
        });
    }
}
