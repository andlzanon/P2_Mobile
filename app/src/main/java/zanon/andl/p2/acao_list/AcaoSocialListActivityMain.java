package zanon.andl.p2.acao_list;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zanon.andl.p2.R;
import zanon.andl.p2.acao_detail.AcaoSocialDetailActivity;
import zanon.andl.p2.database.AcaoSocialRepositorio;
import zanon.andl.p2.entity.AcaoSocialEntity;

public class AcaoSocialListActivityMain extends AppCompatActivity implements AcaoSocialListView {

    //chave responsavel por passar objetos Acao Social entity entre activities
    public final static String EXTRA_ACAOSOCIAL = "acao_social";

    //binding com butterknife
    @BindView(R.id.rv_as)
    RecyclerView mRecyclerView;

    //binding da toolbar
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //binding da progressbar
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    //declaracoes
    public AcaoSocialAdapter mAdapterAcoes;
    private RecyclerView.LayoutManager mLayoutManager;
    private DividerItemDecoration mDividerItemDecoration;
    private AcaoSocialListPresenter presenter;
    private List<AcaoSocialEntity> asList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        asList = new ArrayList<>();

        //seta a action bar no topo da tela
        setSupportActionBar(toolbar);
        //define o nome da action bar
        getSupportActionBar().setTitle(R.string.app_name);

        //seta um adapter default para nao acusar erro de RecyclerView sem adapter
        mRecyclerView.setAdapter(new AcaoSocialAdapter(asList, this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adiciona linha de separacao na RecyclerView
        mDividerItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL
        );
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        boolean online = isOnline();

        if(online){
            presenter = new AcaoSocialListPresenter(this);
            presenter.acessaDados();
            Log.d("Passando", "Aqui");
        }
        else{
            acessaDadosDoBD();
        }
    }

    /**
     * verifica se celular esta online
     * @return se celular esta online
     */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void acessaDadosDoBD(){

        stopProgressBar();
        AcaoSocialRepositorio repositorio = new AcaoSocialRepositorio(this);
        repositorio.listaAcoes(asList);

        if(asList.size() == 0)
            Toast.makeText(this, "Não existem dados salvos no BD, " +
                    "Conecte-se a internet", Toast.LENGTH_SHORT).show();
        else{
            mAdapterAcoes = new AcaoSocialAdapter(asList, this);
            mAdapterAcoes.setOnRecyclerItemClick(new OnRecyclerItemClick() {
                @Override
                public void onClick(View view, int position) {
                    //caso algum elemento seja clicado passa-se o gameEntity para a proxima Activity
                    Intent intent = new Intent(AcaoSocialListActivityMain.this, AcaoSocialDetailActivity.class);
                    intent.putExtra(EXTRA_ACAOSOCIAL, asList.get(position));
                    startActivity(intent);
                }
            });
            mRecyclerView.setAdapter(mAdapterAcoes);

            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
    }

    /**
     * Funcao que define a recyclerView e coloca a lista de acoes sociais para ser exibida
     * @param lista de acoes sociais recebidas pela web para a RecyclerView
     */
    @Override
    public void atualizaLista(List<AcaoSocialEntity> lista) {
        //seta a lista da classe como a lista que vem da web
        asList = lista;

        AcaoSocialRepositorio repositorio = new AcaoSocialRepositorio(this);
        repositorio.excluiBD();
        repositorio.inserirLista(asList);

        mAdapterAcoes = new AcaoSocialAdapter(asList, this);
        mAdapterAcoes.setOnRecyclerItemClick(new OnRecyclerItemClick() {
            @Override
            public void onClick(View view, int position) {
                //caso algum elemento seja clicado passa-se o gameEntity para a proxima Activity
                Intent intent = new Intent(AcaoSocialListActivityMain.this, AcaoSocialDetailActivity.class);
                intent.putExtra(EXTRA_ACAOSOCIAL, asList.get(position));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapterAcoes);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    /**
     * Funcao que mostra mensagem de erro
     * @param mensagem a ser exibida pelo toast
     */
    @Override
    public void mensagemDeErro(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param id da String dos Recursos que se quer acessar
     * @return String correspondente ao id da classe R
     */
    @Override
    public String getTextFromR(int id) {
        return getResources().getString(id);
    }

    /**
     * Funcao que inicia a animacao da progressBar
     */
    @Override
    public void initProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Funcao que para a animacao e esconde a progressBar
     */
    @Override
    public void stopProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
