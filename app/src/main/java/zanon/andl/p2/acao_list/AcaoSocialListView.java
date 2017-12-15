package zanon.andl.p2.acao_list;

import java.util.List;

import zanon.andl.p2.entity.AcaoSocialEntity;

/**
 * Created by Andre on 15/12/2017.
 */

public interface AcaoSocialListView {
    //funcao que atualiza a lista com os dados da API
    public void atualizaLista(List<AcaoSocialEntity> lista);
    //mostra toast com mensagem de erro
    public void mensagemDeErro(String mensagem);
    //acessa os recursos do app para acessar uma String a partide do id da classe R
    public String getTextFromR(int id);
    //incia a animacao da progressBar
    public void initProgressBar();
    //para a animacao da progressBar
    public void stopProgressBar();
}
