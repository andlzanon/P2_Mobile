package zanon.andl.p2.acao_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zanon.andl.p2.R;
import zanon.andl.p2.entity.AcaoSocialEntity;

/**
 * Created by Andre on 15/12/2017.
 */

public class AcaoSocialAdapter extends RecyclerView.Adapter<AcaoSocialAdapter.AcaoSocialViewHolder> {

    OnRecyclerItemClick onRecyclerItemClick;

    /**
     * GamesViewHolder e a classe que representa cada item da lista
     */
    public class AcaoSocialViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.fotoAs)
        ImageView imgAcao;

        @BindView(R.id.nomeAs)
        TextView txtNome;

        public AcaoSocialViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @OnClick(R.id.item_recycler)
        void onItemClick(View view){
            if(onRecyclerItemClick != null){
                onRecyclerItemClick.onClick(view, getAdapterPosition());
            }
        }
    }

    private List<AcaoSocialEntity> asList;
    private Context context;
    private Target target;

    /**
     * Construtor para o Adapter
     * @param asList lista de Games
     * @param context contexto da activity
     */
    public AcaoSocialAdapter(List<AcaoSocialEntity> asList, Context context){
        this.asList = asList;
        this.context = context;
    }

    /**
     * Cria uma instancia de layout para o item da lista
     * @param parent layout pai da ViewHolder
     * @param viewType
     * @return
     */
    @Override
    public AcaoSocialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.acoes_sociais_view, parent, false);
        AcaoSocialViewHolder contatosViewHolder = new AcaoSocialViewHolder(layout);
        return contatosViewHolder;
    }

    /**
     *  Faz os bindigs do layout criado pela onCreateViewHolder
     * @param holder item conforme definido na classe
     * @param position posicao do holder, ou seja em que posicao da lista sera exibido
     */
    @Override
    public void onBindViewHolder(final AcaoSocialViewHolder holder, final int position) {
        AcaoSocialEntity acaoSocialEntity  = asList.get(position);
        holder.txtNome.setText(acaoSocialEntity.getName());
        Picasso.with(context)
                .load(acaoSocialEntity.getImage())
                .centerCrop()
                .fit()
                .into(holder.imgAcao);
    }

    /**
     *
     * @return quantidade de itens a serem mostrados
     */
    @Override
    public int getItemCount() {
        return asList.size();
    }

    public void setOnRecyclerItemClick(OnRecyclerItemClick onRecyclerItemClick){
        this.onRecyclerItemClick = onRecyclerItemClick;
    }
}
