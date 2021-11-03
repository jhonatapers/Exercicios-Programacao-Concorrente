import java.util.concurrent.Semaphore;

public class Reader extends Thread {

    Resource resource;
    Lightswitch readLightswitch;
    Semaphore roomEmpty;

    public Reader(Resource resource, Lightswitch readLightswitch, Semaphore roomEmpty) {
        this.resource = resource;
        this.readLightswitch = readLightswitch;
        this.roomEmpty = roomEmpty;
    }
    
    @Override
    public void run() {
        while(true) {
            readLightswitch.lock(roomEmpty);
            System.out.println(resource.value());
            readLightswitch.unlock(roomEmpty);            
        }
    }
}