# jme-clj-earth

A Clojure library designed to render planet Earth in [jme-clj](https://github.com/ertugrulcetin/jme-clj); intended as a building block towards [climate-game](https://github.com/simon-brooke/climate-game).

## Discussion

### Fundamental geometry

It appears that the jMonkeyEngine [Sphere](https://javadoc.jmonkeyengine.org/v3.4.0-stable/com/jme3/scene/shape/Sphere.html) class - wrapped in jme-clj by the `sphere` function - is essentially a stack of disks. That is to say, it's a collection of vectors, but these vectors are arranged into flat spoked wheels, which are then stacked to make a sphere.

The [particular code](https://github.com/jMonkeyEngine/jmonkeyengine/blob/master/jme3-core/src/main/java/com/jme3/scene/shape/Sphere.java#L195) which leads me to believe this is:

```java
            // compute center of slice
            Vector3f kSliceCenter = tempVb.set(Vector3f.ZERO);
            kSliceCenter.z += fZ;
```

That is to say, the centre point of each `slice` (wheel) is displaced vertically from the origin in Z.

This is almost certainly an efficient means to render a smooth sphere. However, it's not an easy geometry to modify by mapping a heightmap on to it.

What I think is needed is, instead of a stack of wheels, to have a stack of cones - so that all vectors 'start from' (have a common end point at) a given centre; specifically, at the coordinates `{:x 0, :y 0, :z 0}`. This means that each 'spoke' is normal to the surface of the sphere at the point at which it intersects, and that, consequently, a heightmap can be applied to the surface of the sphere simply by lengthening or shortening spokes based on equivalent heightmap values.

### Why this naive approach doesn't work

The Earth is 40,000 km in circumference and has surface features which we observe certainly down to centimetre scale, which means that to map the surface adequately to give verisimilitude, we need a heightmap 2,000,000,000 by 4,000,000,000 (and we'd also need far more than 256 values in the Z axis). Which means we need eight quintillion vectors. While jMonkeyEngine actually copes quite well and is quite performant with a million vectors, the performance starts to drop off quite noticable above that.

This is the problem that the jMonkeyEngine [Terrain](https://javadoc.jmonkeyengine.org/v3.4.0-stable/com/jme3/terrain/Terrain.html) system is designed to address, and I need to learn how to use that!

## Other projects

Other projects I am aware of that attempt to render planets using jMonkeyEngine include

* **sameer-s' [JMEGame](https://github.com/sameer-s/JMEGame)** This does not compile for me, although it could probably be made to do so.
* **Aaron Perkins' [jmeplanet](https://code.google.com/archive/p/jme-planet/)** This does not build for me, because assets are missing from the archive, but there is impressive [video evidence](https://youtu.be/Si4P8ACTFyg) that it certainly did work.

Both of these are worth studying.


## Usage

Compile using

> lein uberjar

Run using

> java -jar target/jme-clj-earth-0.1.0-SNAPSHOT-standalone.jar

Doesn't (yet) do anything very interesting.

## License

Copyright © 2021 Simon Brooke simon@journeyman.cc

This program and the accompanying materials are made available under the
terms of the GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
