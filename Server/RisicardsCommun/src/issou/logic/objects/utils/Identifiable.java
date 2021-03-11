package issou.logic.objects.utils;

import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public abstract class Identifiable implements SerializableSFSType {

    private static int actualId = 1;
    private final int id;

    public Identifiable(){
        this.id = actualId++;
    }

    public SFSObject toSFSObject(){
        SFSObject obj = new SFSObject();
        obj.putInt("id", id);
        return obj;
    }
    public int getId() {
        return this.id;
    }
}
