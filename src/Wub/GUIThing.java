package Wub;

public class GUIThing {
    int index;
    Thing thing;
    int counter = 1;

    public String toString(){return thing.toString() + " (" + counter + ")";}

    public GUIThing(Thing _thing, int _index){
        thing = _thing;
        index = _index;
    }
}
