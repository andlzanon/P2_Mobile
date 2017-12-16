package zanon.andl.p2.acao_list;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zanon.andl.p2.R;
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
     *
     * @param conectado variavel que representa se celular esta ou nao conectado
     */
    public void BDouInternet(Boolean conectado){
        if(conectado){
            acessaDados();
        }
        else{
            acaoSocialListView.BDparaLista();
        }
    }

    /**
     * verifica se lista e consequentemente BD esta vazio
     * @param list
     */
    public void listaVazia(List list){
        //se esta vazio mostra mensagem de erro
        if(list.size() == 0){
            acaoSocialListView.mensagemDeErro(acaoSocialListView.getTextFromR(R.string.erro_bd));
        }
        //senao mostra lista na recycler
        else{
            acaoSocialListView.ListaparaRecycler();
        }
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
