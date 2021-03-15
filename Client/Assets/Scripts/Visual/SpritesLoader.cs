using System.Collections.Generic;
using UnityEngine;

public static class SpritesLoader {
        
        private static readonly Dictionary<string, Sprite> Sprites = new Dictionary<string, Sprite>();

        public static Sprite Get(string name) {
            if (Sprites.ContainsKey(name)) return Sprites[name];
            Sprite r = Resources.Load<Sprite>("Art/" + name);
            Sprites.Add(name, r);
            return Sprites[name];
        }
}
