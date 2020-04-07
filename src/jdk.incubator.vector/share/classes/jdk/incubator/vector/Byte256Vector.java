/*
 * Copyright (c) 2017, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have
 * questions.
 */
package jdk.incubator.vector;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntUnaryOperator;

import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.vector.VectorSupport;

import static jdk.internal.vm.vector.VectorSupport.*;

import static jdk.incubator.vector.VectorOperators.*;

// -- This file was mechanically generated: Do not edit! -- //

@SuppressWarnings("cast")  // warning: redundant cast
final class Byte256Vector extends ByteVector {
    static final ByteSpecies VSPECIES =
        (ByteSpecies) ByteVector.SPECIES_256;

    static final VectorShape VSHAPE =
        VSPECIES.vectorShape();

    static final Class<Byte256Vector> VCLASS = Byte256Vector.class;

    static final int VSIZE = VSPECIES.vectorBitSize();

    static final int VLENGTH = VSPECIES.laneCount(); // used by the JVM

    static final Class<Byte> ETYPE = byte.class; // used by the JVM

    Byte256Vector(byte[] v) {
        super(v);
    }

    // For compatibility as Byte256Vector::new,
    // stored into species.vectorFactory.
    Byte256Vector(Object v) {
        this((byte[]) v);
    }

    static final Byte256Vector ZERO = new Byte256Vector(new byte[VLENGTH]);
    static final Byte256Vector IOTA = new Byte256Vector(VSPECIES.iotaArray());

    static {
        // Warm up a few species caches.
        // If we do this too much we will
        // get NPEs from bootstrap circularity.
        VSPECIES.dummyVector();
        VSPECIES.withLanes(LaneType.BYTE);
    }

    // Specialized extractors

    @ForceInline
    final @Override
    public ByteSpecies vspecies() {
        // ISSUE:  This should probably be a @Stable
        // field inside AbstractVector, rather than
        // a megamorphic method.
        return VSPECIES;
    }

    @ForceInline
    @Override
    public final Class<Byte> elementType() { return byte.class; }

    @ForceInline
    @Override
    public final int elementSize() { return Byte.SIZE; }

    @ForceInline
    @Override
    public final VectorShape shape() { return VSHAPE; }

    @ForceInline
    @Override
    public final int length() { return VLENGTH; }

    @ForceInline
    @Override
    public final int bitSize() { return VSIZE; }

    @ForceInline
    @Override
    public final int byteSize() { return VSIZE / Byte.SIZE; }

    /*package-private*/
    @ForceInline
    final @Override
    byte[] vec() {
        return (byte[])getPayload();
    }

    // Virtualized constructors

    @Override
    @ForceInline
    public final Byte256Vector broadcast(byte e) {
        return (Byte256Vector) super.broadcastTemplate(e);  // specialize
    }

    @Override
    @ForceInline
    public final Byte256Vector broadcast(long e) {
        return (Byte256Vector) super.broadcastTemplate(e);  // specialize
    }

    @Override
    @ForceInline
    Byte256Mask maskFromArray(boolean[] bits) {
        return new Byte256Mask(bits);
    }

    @Override
    @ForceInline
    Byte256Shuffle iotaShuffle() { return Byte256Shuffle.IOTA; }

    @ForceInline
    Byte256Shuffle iotaShuffle(int start, int step, boolean wrap) {
      if (wrap) {
        return (Byte256Shuffle)VectorSupport.shuffleIota(ETYPE, Byte256Shuffle.class, VSPECIES, VLENGTH, start, step, 1, (l, lstart, lstep) -> new Byte256Shuffle(i -> (VectorIntrinsics.wrapToRange(i*lstep + lstart, l))));
      } else {
        return (Byte256Shuffle)VectorSupport.shuffleIota(ETYPE, Byte256Shuffle.class, VSPECIES, VLENGTH, start, step, 0, (l, lstart, lstep) -> new Byte256Shuffle(i -> (i*lstep + lstart)));
      }
    }

    @Override
    @ForceInline
    Byte256Shuffle shuffleFromBytes(byte[] reorder) { return new Byte256Shuffle(reorder); }

    @Override
    @ForceInline
    Byte256Shuffle shuffleFromArray(int[] indexes, int i) { return new Byte256Shuffle(indexes, i); }

    @Override
    @ForceInline
    Byte256Shuffle shuffleFromOp(IntUnaryOperator fn) { return new Byte256Shuffle(fn); }

    // Make a vector of the same species but the given elements:
    @ForceInline
    final @Override
    Byte256Vector vectorFactory(byte[] vec) {
        return new Byte256Vector(vec);
    }

    @ForceInline
    final @Override
    Byte256Vector asByteVectorRaw() {
        return (Byte256Vector) super.asByteVectorRawTemplate();  // specialize
    }

    @ForceInline
    final @Override
    AbstractVector<?> asVectorRaw(LaneType laneType) {
        return super.asVectorRawTemplate(laneType);  // specialize
    }

    // Unary operator

    @ForceInline
    final @Override
    Byte256Vector uOp(FUnOp f) {
        return (Byte256Vector) super.uOpTemplate(f);  // specialize
    }

    @ForceInline
    final @Override
    Byte256Vector uOp(VectorMask<Byte> m, FUnOp f) {
        return (Byte256Vector)
            super.uOpTemplate((Byte256Mask)m, f);  // specialize
    }

    // Binary operator

    @ForceInline
    final @Override
    Byte256Vector bOp(Vector<Byte> v, FBinOp f) {
        return (Byte256Vector) super.bOpTemplate((Byte256Vector)v, f);  // specialize
    }

    @ForceInline
    final @Override
    Byte256Vector bOp(Vector<Byte> v,
                     VectorMask<Byte> m, FBinOp f) {
        return (Byte256Vector)
            super.bOpTemplate((Byte256Vector)v, (Byte256Mask)m,
                              f);  // specialize
    }

    // Ternary operator

    @ForceInline
    final @Override
    Byte256Vector tOp(Vector<Byte> v1, Vector<Byte> v2, FTriOp f) {
        return (Byte256Vector)
            super.tOpTemplate((Byte256Vector)v1, (Byte256Vector)v2,
                              f);  // specialize
    }

    @ForceInline
    final @Override
    Byte256Vector tOp(Vector<Byte> v1, Vector<Byte> v2,
                     VectorMask<Byte> m, FTriOp f) {
        return (Byte256Vector)
            super.tOpTemplate((Byte256Vector)v1, (Byte256Vector)v2,
                              (Byte256Mask)m, f);  // specialize
    }

    @ForceInline
    final @Override
    byte rOp(byte v, FBinOp f) {
        return super.rOpTemplate(v, f);  // specialize
    }

    @Override
    @ForceInline
    public final <F>
    Vector<F> convertShape(VectorOperators.Conversion<Byte,F> conv,
                           VectorSpecies<F> rsp, int part) {
        return super.convertShapeTemplate(conv, rsp, part);  // specialize
    }

    @Override
    @ForceInline
    public final <F>
    Vector<F> reinterpretShape(VectorSpecies<F> toSpecies, int part) {
        return super.reinterpretShapeTemplate(toSpecies, part);  // specialize
    }

    // Specialized algebraic operations:

    // The following definition forces a specialized version of this
    // crucial method into the v-table of this class.  A call to add()
    // will inline to a call to lanewise(ADD,), at which point the JIT
    // intrinsic will have the opcode of ADD, plus all the metadata
    // for this particular class, enabling it to generate precise
    // code.
    //
    // There is probably no benefit to the JIT to specialize the
    // masked or broadcast versions of the lanewise method.

    @Override
    @ForceInline
    public Byte256Vector lanewise(Unary op) {
        return (Byte256Vector) super.lanewiseTemplate(op);  // specialize
    }

    @Override
    @ForceInline
    public Byte256Vector lanewise(Binary op, Vector<Byte> v) {
        return (Byte256Vector) super.lanewiseTemplate(op, v);  // specialize
    }

    /*package-private*/
    @Override
    @ForceInline Byte256Vector
    lanewiseShift(VectorOperators.Binary op, int e) {
        return (Byte256Vector) super.lanewiseShiftTemplate(op, e);  // specialize
    }

    /*package-private*/
    @Override
    @ForceInline
    public final
    Byte256Vector
    lanewise(VectorOperators.Ternary op, Vector<Byte> v1, Vector<Byte> v2) {
        return (Byte256Vector) super.lanewiseTemplate(op, v1, v2);  // specialize
    }

    @Override
    @ForceInline
    public final
    Byte256Vector addIndex(int scale) {
        return (Byte256Vector) super.addIndexTemplate(scale);  // specialize
    }

    // Type specific horizontal reductions

    @Override
    @ForceInline
    public final byte reduceLanes(VectorOperators.Associative op) {
        return super.reduceLanesTemplate(op);  // specialized
    }

    @Override
    @ForceInline
    public final byte reduceLanes(VectorOperators.Associative op,
                                    VectorMask<Byte> m) {
        return super.reduceLanesTemplate(op, m);  // specialized
    }

    @Override
    @ForceInline
    public final long reduceLanesToLong(VectorOperators.Associative op) {
        return (long) super.reduceLanesTemplate(op);  // specialized
    }

    @Override
    @ForceInline
    public final long reduceLanesToLong(VectorOperators.Associative op,
                                        VectorMask<Byte> m) {
        return (long) super.reduceLanesTemplate(op, m);  // specialized
    }

    @Override
    @ForceInline
    public VectorShuffle<Byte> toShuffle() {
        byte[] a = toArray();
        int[] sa = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            sa[i] = (int) a[i];
        }
        return VectorShuffle.fromArray(VSPECIES, sa, 0);
    }

    // Specialized unary testing

    @Override
    @ForceInline
    public final Byte256Mask test(Test op) {
        return super.testTemplate(Byte256Mask.class, op);  // specialize
    }

    // Specialized comparisons

    @Override
    @ForceInline
    public final Byte256Mask compare(Comparison op, Vector<Byte> v) {
        return super.compareTemplate(Byte256Mask.class, op, v);  // specialize
    }

    @Override
    @ForceInline
    public final Byte256Mask compare(Comparison op, byte s) {
        return super.compareTemplate(Byte256Mask.class, op, s);  // specialize
    }

    @Override
    @ForceInline
    public final Byte256Mask compare(Comparison op, long s) {
        return super.compareTemplate(Byte256Mask.class, op, s);  // specialize
    }

    @Override
    @ForceInline
    public Byte256Vector blend(Vector<Byte> v, VectorMask<Byte> m) {
        return (Byte256Vector)
            super.blendTemplate(Byte256Mask.class,
                                (Byte256Vector) v,
                                (Byte256Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Byte256Vector slice(int origin, Vector<Byte> v) {
        return (Byte256Vector) super.sliceTemplate(origin, v);  // specialize
    }

    @Override
    @ForceInline
    public Byte256Vector slice(int origin) {
       if ((origin < 0) || (origin >= VLENGTH)) {
         throw new ArrayIndexOutOfBoundsException("Index " + origin + " out of bounds for vector length " + VLENGTH);
       } else {
         Byte256Shuffle Iota = (Byte256Shuffle)VectorShuffle.iota(VSPECIES, 0, 1, true);
         VectorMask<Byte> BlendMask = Iota.toVector().compare(VectorOperators.LT, (broadcast((byte)(VLENGTH-origin))));
         Iota = (Byte256Shuffle)VectorShuffle.iota(VSPECIES, origin, 1, true);
         return ZERO.blend(this.rearrange(Iota), BlendMask);
       }
    }

    @Override
    @ForceInline
    public Byte256Vector unslice(int origin, Vector<Byte> w, int part) {
        return (Byte256Vector) super.unsliceTemplate(origin, w, part);  // specialize
    }

    @Override
    @ForceInline
    public Byte256Vector unslice(int origin, Vector<Byte> w, int part, VectorMask<Byte> m) {
        return (Byte256Vector)
            super.unsliceTemplate(Byte256Mask.class,
                                  origin, w, part,
                                  (Byte256Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Byte256Vector unslice(int origin) {
       if ((origin < 0) || (origin >= VLENGTH)) {
         throw new ArrayIndexOutOfBoundsException("Index " + origin + " out of bounds for vector length " + VLENGTH);
       } else {
         Byte256Shuffle Iota = (Byte256Shuffle)VectorShuffle.iota(VSPECIES, 0, 1, true);
         VectorMask<Byte> BlendMask = Iota.toVector().compare(VectorOperators.GE, (broadcast((byte)(origin))));
         Iota = (Byte256Shuffle)VectorShuffle.iota(VSPECIES, -origin, 1, true);
         return ZERO.blend(this.rearrange(Iota), BlendMask);
       }
    }

    @Override
    @ForceInline
    public Byte256Vector rearrange(VectorShuffle<Byte> s) {
        return (Byte256Vector)
            super.rearrangeTemplate(Byte256Shuffle.class,
                                    (Byte256Shuffle) s);  // specialize
    }

    @Override
    @ForceInline
    public Byte256Vector rearrange(VectorShuffle<Byte> shuffle,
                                  VectorMask<Byte> m) {
        return (Byte256Vector)
            super.rearrangeTemplate(Byte256Shuffle.class,
                                    (Byte256Shuffle) shuffle,
                                    (Byte256Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Byte256Vector rearrange(VectorShuffle<Byte> s,
                                  Vector<Byte> v) {
        return (Byte256Vector)
            super.rearrangeTemplate(Byte256Shuffle.class,
                                    (Byte256Shuffle) s,
                                    (Byte256Vector) v);  // specialize
    }

    @Override
    @ForceInline
    public Byte256Vector selectFrom(Vector<Byte> v) {
        return (Byte256Vector)
            super.selectFromTemplate((Byte256Vector) v);  // specialize
    }

    @Override
    @ForceInline
    public Byte256Vector selectFrom(Vector<Byte> v,
                                   VectorMask<Byte> m) {
        return (Byte256Vector)
            super.selectFromTemplate((Byte256Vector) v,
                                     (Byte256Mask) m);  // specialize
    }


    @ForceInline
    @Override
    public byte lane(int i) {
        if (i < 0 || i >= VLENGTH) {
            throw new IllegalArgumentException("Index " + i + " must be zero or positive, and less than " + VLENGTH);
        }
        return (byte) VectorSupport.extract(
                                VCLASS, ETYPE, VLENGTH,
                                this, i,
                                (vec, ix) -> {
                                    byte[] vecarr = vec.vec();
                                    return (long)vecarr[ix];
                                });
    }

    @ForceInline
    @Override
    public Byte256Vector withLane(int i, byte e) {
        if (i < 0 || i >= VLENGTH) {
            throw new IllegalArgumentException("Index " + i + " must be zero or positive, and less than " + VLENGTH);
        }
        return VectorSupport.insert(
                                VCLASS, ETYPE, VLENGTH,
                                this, i, (long)e,
                                (v, ix, bits) -> {
                                    byte[] res = v.vec().clone();
                                    res[ix] = (byte)bits;
                                    return v.vectorFactory(res);
                                });
    }

    // Mask

    static final class Byte256Mask extends AbstractMask<Byte> {
        static final int VLENGTH = VSPECIES.laneCount();    // used by the JVM
        static final Class<Byte> ETYPE = byte.class; // used by the JVM

        Byte256Mask(boolean[] bits) {
            this(bits, 0);
        }

        Byte256Mask(boolean[] bits, int offset) {
            super(prepare(bits, offset));
        }

        Byte256Mask(boolean val) {
            super(prepare(val));
        }

        private static boolean[] prepare(boolean[] bits, int offset) {
            boolean[] newBits = new boolean[VSPECIES.laneCount()];
            for (int i = 0; i < newBits.length; i++) {
                newBits[i] = bits[offset + i];
            }
            return newBits;
        }

        private static boolean[] prepare(boolean val) {
            boolean[] bits = new boolean[VSPECIES.laneCount()];
            Arrays.fill(bits, val);
            return bits;
        }

        @ForceInline
        final @Override
        public ByteSpecies vspecies() {
            // ISSUE:  This should probably be a @Stable
            // field inside AbstractMask, rather than
            // a megamorphic method.
            return VSPECIES;
        }

        @ForceInline
        boolean[] getBits() {
            return (boolean[])getPayload();
        }

        @Override
        Byte256Mask uOp(MUnOp f) {
            boolean[] res = new boolean[vspecies().laneCount()];
            boolean[] bits = getBits();
            for (int i = 0; i < res.length; i++) {
                res[i] = f.apply(i, bits[i]);
            }
            return new Byte256Mask(res);
        }

        @Override
        Byte256Mask bOp(VectorMask<Byte> m, MBinOp f) {
            boolean[] res = new boolean[vspecies().laneCount()];
            boolean[] bits = getBits();
            boolean[] mbits = ((Byte256Mask)m).getBits();
            for (int i = 0; i < res.length; i++) {
                res[i] = f.apply(i, bits[i], mbits[i]);
            }
            return new Byte256Mask(res);
        }

        @ForceInline
        @Override
        public final
        Byte256Vector toVector() {
            return (Byte256Vector) super.toVectorTemplate();  // specialize
        }

        @Override
        @ForceInline
        public <E> VectorMask<E> cast(VectorSpecies<E> s) {
            AbstractSpecies<E> species = (AbstractSpecies<E>) s;
            if (length() != species.laneCount())
                throw new IllegalArgumentException("VectorMask length and species length differ");
            boolean[] maskArray = toArray();
            // enum-switches don't optimize properly JDK-8161245
            switch (species.laneType.switchKey) {
            case LaneType.SK_BYTE:
                return new Byte256Vector.Byte256Mask(maskArray).check(species);
            case LaneType.SK_SHORT:
                return new Short256Vector.Short256Mask(maskArray).check(species);
            case LaneType.SK_INT:
                return new Int256Vector.Int256Mask(maskArray).check(species);
            case LaneType.SK_LONG:
                return new Long256Vector.Long256Mask(maskArray).check(species);
            case LaneType.SK_FLOAT:
                return new Float256Vector.Float256Mask(maskArray).check(species);
            case LaneType.SK_DOUBLE:
                return new Double256Vector.Double256Mask(maskArray).check(species);
            }

            // Should not reach here.
            throw new AssertionError(species);
        }

        // Unary operations

        @Override
        @ForceInline
        public Byte256Mask not() {
            return (Byte256Mask) VectorSupport.unaryOp(
                                             VECTOR_OP_NOT, Byte256Mask.class, byte.class, VLENGTH,
                                             this,
                                             (m1) -> m1.uOp((i, a) -> !a));
        }

        // Binary operations

        @Override
        @ForceInline
        public Byte256Mask and(VectorMask<Byte> mask) {
            Objects.requireNonNull(mask);
            Byte256Mask m = (Byte256Mask)mask;
            return VectorSupport.binaryOp(VECTOR_OP_AND, Byte256Mask.class, byte.class, VLENGTH,
                                             this, m,
                                             (m1, m2) -> m1.bOp(m2, (i, a, b) -> a & b));
        }

        @Override
        @ForceInline
        public Byte256Mask or(VectorMask<Byte> mask) {
            Objects.requireNonNull(mask);
            Byte256Mask m = (Byte256Mask)mask;
            return VectorSupport.binaryOp(VECTOR_OP_OR, Byte256Mask.class, byte.class, VLENGTH,
                                             this, m,
                                             (m1, m2) -> m1.bOp(m2, (i, a, b) -> a | b));
        }

        // Reductions

        @Override
        @ForceInline
        public boolean anyTrue() {
            return VectorSupport.test(BT_ne, Byte256Mask.class, byte.class, VLENGTH,
                                         this, vspecies().maskAll(true),
                                         (m, __) -> anyTrueHelper(((Byte256Mask)m).getBits()));
        }

        @Override
        @ForceInline
        public boolean allTrue() {
            return VectorSupport.test(BT_overflow, Byte256Mask.class, byte.class, VLENGTH,
                                         this, vspecies().maskAll(true),
                                         (m, __) -> allTrueHelper(((Byte256Mask)m).getBits()));
        }

        /*package-private*/
        static Byte256Mask maskAll(boolean bit) {
            return bit ? TRUE_MASK : FALSE_MASK;
        }
        static final Byte256Mask TRUE_MASK = new Byte256Mask(true);
        static final Byte256Mask FALSE_MASK = new Byte256Mask(false);
    }

    // Shuffle

    static final class Byte256Shuffle extends AbstractShuffle<Byte> {
        static final int VLENGTH = VSPECIES.laneCount();    // used by the JVM
        static final Class<Byte> ETYPE = byte.class; // used by the JVM

        Byte256Shuffle(byte[] reorder) {
            super(VLENGTH, reorder);
        }

        public Byte256Shuffle(int[] reorder) {
            super(VLENGTH, reorder);
        }

        public Byte256Shuffle(int[] reorder, int i) {
            super(VLENGTH, reorder, i);
        }

        public Byte256Shuffle(IntUnaryOperator fn) {
            super(VLENGTH, fn);
        }

        @Override
        public ByteSpecies vspecies() {
            return VSPECIES;
        }

        static {
            // There must be enough bits in the shuffle lanes to encode
            // VLENGTH valid indexes and VLENGTH exceptional ones.
            assert(VLENGTH < Byte.MAX_VALUE);
            assert(Byte.MIN_VALUE <= -VLENGTH);
        }
        static final Byte256Shuffle IOTA = new Byte256Shuffle(IDENTITY);

        @Override
        @ForceInline
        public Byte256Vector toVector() {
            return VectorSupport.shuffleToVector(VCLASS, ETYPE, Byte256Shuffle.class, this, VLENGTH,
                                                    (s) -> ((Byte256Vector)(((AbstractShuffle<Byte>)(s)).toVectorTemplate())));
        }

        @Override
        @ForceInline
        public <F> VectorShuffle<F> cast(VectorSpecies<F> s) {
            AbstractSpecies<F> species = (AbstractSpecies<F>) s;
            if (length() != species.laneCount())
                throw new IllegalArgumentException("VectorShuffle length and species length differ");
            int[] shuffleArray = toArray();
            // enum-switches don't optimize properly JDK-8161245
            switch (species.laneType.switchKey) {
            case LaneType.SK_BYTE:
                return new Byte256Vector.Byte256Shuffle(shuffleArray).check(species);
            case LaneType.SK_SHORT:
                return new Short256Vector.Short256Shuffle(shuffleArray).check(species);
            case LaneType.SK_INT:
                return new Int256Vector.Int256Shuffle(shuffleArray).check(species);
            case LaneType.SK_LONG:
                return new Long256Vector.Long256Shuffle(shuffleArray).check(species);
            case LaneType.SK_FLOAT:
                return new Float256Vector.Float256Shuffle(shuffleArray).check(species);
            case LaneType.SK_DOUBLE:
                return new Double256Vector.Double256Shuffle(shuffleArray).check(species);
            }

            // Should not reach here.
            throw new AssertionError(species);
        }

        @ForceInline
        @Override
        public Byte256Shuffle rearrange(VectorShuffle<Byte> shuffle) {
            Byte256Shuffle s = (Byte256Shuffle) shuffle;
            byte[] reorder1 = reorder();
            byte[] reorder2 = s.reorder();
            byte[] r = new byte[reorder1.length];
            for (int i = 0; i < reorder1.length; i++) {
                int ssi = reorder2[i];
                r[i] = reorder1[ssi];  // throws on exceptional index
            }
            return new Byte256Shuffle(r);
        }
    }

    // ================================================

    // Specialized low-level memory operations.

    @ForceInline
    @Override
    final
    ByteVector fromArray0(byte[] a, int offset) {
        return super.fromArray0Template(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    ByteVector fromByteArray0(byte[] a, int offset) {
        return super.fromByteArray0Template(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    ByteVector fromByteBuffer0(ByteBuffer bb, int offset) {
        return super.fromByteBuffer0Template(bb, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    void intoArray0(byte[] a, int offset) {
        super.intoArray0Template(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    void intoByteArray0(byte[] a, int offset) {
        super.intoByteArray0Template(a, offset);  // specialize
    }

    // End of specialized low-level memory operations.

    // ================================================

}
