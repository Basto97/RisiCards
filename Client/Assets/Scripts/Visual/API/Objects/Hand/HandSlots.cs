using UnityEngine;

public class HandSlots : MonoBehaviour {
        
    public Transform[] transforms;
    
    private void Awake() {
        Vector3 firstElementPos = transforms[0].transform.position;
        Vector3 lastElementPos = transforms[transforms.Length - 1].transform.position;
        
        float xDist = (lastElementPos.x - firstElementPos.x)/(transforms.Length - 1);
        float yDist = (lastElementPos.y - firstElementPos.y)/(transforms.Length - 1);
        float zDist = (lastElementPos.z - firstElementPos.z)/(transforms.Length - 1);

        Vector3 dist = new Vector3(xDist, yDist, zDist);

        for (int i = 1; i < transforms.Length; i++) 
            transforms[i].transform.position = transforms[i - 1].transform.position + dist;
    }
}