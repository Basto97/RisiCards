using UnityEngine;
public class SameDistanceChildren : MonoBehaviour {

    public Transform[] children;
    
	private void Awake () {
        Vector3 firstElementPos = children[0].transform.position;
        Vector3 lastElementPos = children[children.Length - 1].transform.position;
        
        float xDist = (lastElementPos.x - firstElementPos.x)/(children.Length - 1);
        float yDist = (lastElementPos.y - firstElementPos.y)/(children.Length - 1);
        float zDist = (lastElementPos.z - firstElementPos.z)/(children.Length - 1);

        Vector3 dist = new Vector3(xDist, yDist, zDist);

        for (int i = 1; i < children.Length; i++) 
            children[i].transform.position = children[i - 1].transform.position + dist;
	}
}
