package zanon.andl.p2.acao_detail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import zanon.andl.p2.R;
import zanon.andl.p2.entity.AcaoSocialEntity;

import static zanon.andl.p2.acao_list.AcaoSocialListActivityMain.EXTRA_ACAOSOCIAL;

public class AcaoSocialDetailActivity extends AppCompatActivity {

    //instanciacoes dos elementos
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.imagemAs)
    CircleImageView circleImageView;

    @BindView(R.id.nomeDescAs)
    TextView nomeDescAs;

    @BindView(R.id.descricaoAs)
    TextView descricaoAs;

    private AcaoSocialEntity acaoSocialEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao_social_detail);

        ButterKnife.bind(this);

        //recebe os tipos passados por intent
        Intent intent = getIntent();
        acaoSocialEntity = (AcaoSocialEntity) intent.getSerializableExtra(EXTRA_ACAOSOCIAL);

        //seta a toobar e a parent activity
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(acaoSocialEntity.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //carrega imagem
        Picasso.with(this)
                .load(acaoSocialEntity.getImage())
                .centerCrop()
                .fit()
                .into(circleImageView);

        //seta textviews
        nomeDescAs.setText(acaoSocialEntity.getName());
        descricaoAs.setText(acaoSocialEntity.getDescription());
    }

    //define o click no botao com uma intent para abrir o navegador
    @OnClick(R.id.button_site)
    public void onClickButtonSite(){
        Uri uri = Uri.parse(acaoSocialEntity.getSite());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
