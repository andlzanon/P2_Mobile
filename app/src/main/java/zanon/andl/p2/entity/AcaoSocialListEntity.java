
package zanon.andl.p2.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcaoSocialListEntity {

    @SerializedName("acoes_sociais")
    @Expose
    private List<AcaoSocialEntity> acaoSocialEntities = null;

    public List<AcaoSocialEntity> getAcaoSocialEntities() {
        return acaoSocialEntities;
    }

    public void setAcaoSocialEntities(List<AcaoSocialEntity> acaoSocialEntities) {
        this.acaoSocialEntities = acaoSocialEntities;
    }

}
