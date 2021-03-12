using System.Collections.Generic;
using UnityEngine;

public static class SpritesLoader {
        
        private static readonly Dictionary<string, Sprite> Sprites = new Dictionary<string, Sprite>();

        public static Sprite Get(string name) {
            if (!Sprites.ContainsKey(name)) 
                Sprites.Add(name,Resources.Load<Sprite>("Art/"+name+".png"));
            return Sprites[name];
        }
}
