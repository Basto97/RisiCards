package issou;

import com.smartfoxserver.v2.extensions.SFSExtension;
import issou.collection.Content;

public class RisicardsCommunExtension extends SFSExtension {

    private Content content;

    @Override
    public void init() {
        content = new Content();
    }

    public Content getContent(){
        return content;
    }
}
