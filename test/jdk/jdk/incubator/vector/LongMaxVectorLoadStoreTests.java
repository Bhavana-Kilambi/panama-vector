/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
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

/*
 * @test
 * @modules jdk.incubator.vector
 * @run testng/othervm --add-opens jdk.incubator.vector/jdk.incubator.vector=ALL-UNNAMED
 *      LongMaxVectorLoadStoreTests
 *
 */

// -- This file was mechanically generated: Do not edit! -- //

import jdk.incubator.vector.VectorShape;
import jdk.incubator.vector.VectorSpecies;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.Vector;

import jdk.incubator.vector.LongVector;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

@Test
public class LongMaxVectorLoadStoreTests extends AbstractVectorTest {
    static final VectorSpecies<Long> SPECIES =
                LongVector.SPECIES_MAX;

    static final int INVOC_COUNT = Integer.getInteger("jdk.incubator.vector.test.loop-iterations", 10);

    static VectorShape getMaxBit() {
        return VectorShape.S_Max_BIT;
    }

    private static final int Max = 256;  // juts so we can do N/Max

    static final int BUFFER_REPS = Integer.getInteger("jdk.incubator.vector.test.buffer-vectors", 25000 / Max);

    static final int BUFFER_SIZE = Integer.getInteger("jdk.incubator.vector.test.buffer-size", BUFFER_REPS * (Max / 8));

    static void assertArraysEquals(long[] a, long[] r, boolean[] mask) {
        int i = 0;
        try {
            for (; i < a.length; i++) {
                Assert.assertEquals(mask[i % SPECIES.length()] ? a[i] : (long) 0, r[i]);
            }
        } catch (AssertionError e) {
            Assert.assertEquals(mask[i % SPECIES.length()] ? a[i] : (long) 0, r[i], "at index #" + i);
        }
    }

    static void assertArraysEquals(long[] a, long[] r, int[] im) {
        int i = 0;
        try {
            for (; i < a.length; i++) {
                Assert.assertEquals(a[im[i]], r[i]);
            }
        } catch (AssertionError e) {
            Assert.assertEquals(a[im[i]], r[i], "at index #" + i);
        }
    }

    static void assertArraysEquals(long[] a, long[] r, int[] im, boolean[] mask) {
        int i = 0;
        try {
            for (; i < a.length; i++) {
                Assert.assertEquals(mask[i % SPECIES.length()] ? a[im[i]] : (long) 0, r[i]);
            }
        } catch (AssertionError e) {
            Assert.assertEquals(mask[i % SPECIES.length()] ? a[im[i]] : (long) 0, r[i], "at index #" + i);
        }
    }

    static void assertArraysEquals(byte[] a, byte[] r, boolean[] mask) {
        int i = 0;
        try {
            for (; i < a.length; i++) {
                Assert.assertEquals(mask[(i*8/SPECIES.elementSize()) % SPECIES.length()] ? a[i] : (byte) 0, r[i]);
            }
        } catch (AssertionError e) {
            Assert.assertEquals(mask[(i*8/SPECIES.elementSize()) % SPECIES.length()] ? a[i] : (byte) 0, r[i], "at index #" + i);
        }
    }

    static final List<IntFunction<long[]>> LONG_GENERATORS = List.of(
            withToString("long[i * 5]", (int s) -> {
                return fill(s * BUFFER_REPS,
                            i -> (long)(i * 5));
            }),
            withToString("long[i + 1]", (int s) -> {
                return fill(s * BUFFER_REPS,
                            i -> (((long)(i + 1) == 0) ? 1 : (long)(i + 1)));
            })
    );

    @DataProvider
    public Object[][] longProvider() {
        return LONG_GENERATORS.stream().
                map(f -> new Object[]{f}).
                toArray(Object[][]::new);
    }

    @DataProvider
    public Object[][] longMaskProvider() {
        return BOOLEAN_MASK_GENERATORS.stream().
                flatMap(fm -> LONG_GENERATORS.stream().map(fa -> {
                    return new Object[] {fa, fm};
                })).
                toArray(Object[][]::new);
    }

    @DataProvider
    public Object[][] longIndexMapProvider() {
        return INDEX_GENERATORS.stream().
                flatMap(fim -> LONG_GENERATORS.stream().map(fa -> {
                    return new Object[] {fa, fim};
                })).
                toArray(Object[][]::new);
    }

    @DataProvider
    public Object[][] longIndexMapMaskProvider() {
        return BOOLEAN_MASK_GENERATORS.stream().
                flatMap(fm -> INDEX_GENERATORS.stream().
                    flatMap(fim -> LONG_GENERATORS.stream().map(fa -> {
                        return new Object[] {fa, fim, fm};
                }))).
                toArray(Object[][]::new);
    }

    @DataProvider
    public Object[][] longByteBufferProvider() {
        return LONG_GENERATORS.stream().
                flatMap(fa -> BYTE_BUFFER_GENERATORS.stream().map(fb -> {
                    return new Object[]{fa, fb};
                })).
                toArray(Object[][]::new);
    }

    @DataProvider
    public Object[][] longByteBufferMaskProvider() {
        return BOOLEAN_MASK_GENERATORS.stream().
                flatMap(fm -> LONG_GENERATORS.stream().
                        flatMap(fa -> BYTE_BUFFER_GENERATORS.stream().map(fb -> {
                            return new Object[]{fa, fb, fm};
                        }))).
                toArray(Object[][]::new);
    }

    @DataProvider
    public Object[][] longByteArrayProvider() {
        return LONG_GENERATORS.stream().
                flatMap(fa -> BYTE_ARRAY_GENERATORS.stream().map(fb -> {
                    return new Object[]{fa, fb};
                })).
                toArray(Object[][]::new);
    }

    @DataProvider
    public Object[][] longByteArrayMaskProvider() {
        return BOOLEAN_MASK_GENERATORS.stream().
                flatMap(fm -> LONG_GENERATORS.stream().
                        flatMap(fa -> BYTE_ARRAY_GENERATORS.stream().map(fb -> {
                            return new Object[]{fa, fb, fm};
                        }))).
                toArray(Object[][]::new);
    }

    static ByteBuffer toBuffer(long[] a, IntFunction<ByteBuffer> fb) {
        ByteBuffer bb = fb.apply(a.length * SPECIES.elementSize() / 8);
        for (long v : a) {
            bb.putLong(v);
        }
        return bb.clear();
    }

    static long[] bufferToArray(ByteBuffer bb) {
        LongBuffer db = bb.asLongBuffer();
        long[] d = new long[db.capacity()];
        db.get(d);
        return d;
    }

    static byte[] toByteArray(long[] a, IntFunction<byte[]> fb, ByteOrder bo) {
        byte[] b = fb.apply(a.length * SPECIES.elementSize() / 8);
        LongBuffer bb = ByteBuffer.wrap(b, 0, b.length).order(bo).asLongBuffer();
        for (long v : a) {
            bb.put(v);
        }
        return b;
    }


    interface ToLongF {
        long apply(int i);
    }

    static long[] fill(int s , ToLongF f) {
        return fill(new long[s], f);
    }

    static long[] fill(long[] a, ToLongF f) {
        for (int i = 0; i < a.length; i++) {
            a[i] = f.apply(i);
        }
        return a;
    }

    @Test(dataProvider = "longProvider")
    static void loadStoreValues(IntFunction<long[]> fa) {
        long[] a = fa.apply(SPECIES.length());
        long[] r = new long[a.length];

        for (int ic = 0; ic < INVOC_COUNT; ic++) {
            for (int i = 0; i < a.length; i += SPECIES.length()) {
                long[] values = Arrays.copyOfRange(a, i, i + SPECIES.length());
                LongVector av = LongVector.fromValues(SPECIES, values);
                System.arraycopy(av.toArray(), 0, r, i, SPECIES.length());
            }
        }
        Assert.assertEquals(a, r);
    }

    @Test(dataProvider = "longProvider")
    static void loadStoreArray(IntFunction<long[]> fa) {
        long[] a = fa.apply(SPECIES.length());
        long[] r = new long[a.length];

        for (int ic = 0; ic < INVOC_COUNT; ic++) {
            for (int i = 0; i < a.length; i += SPECIES.length()) {
                LongVector av = LongVector.fromArray(SPECIES, a, i);
                av.intoArray(r, i);
            }
        }
        Assert.assertEquals(a, r);
    }

    @Test(dataProvider = "longMaskProvider")
    static void loadStoreMaskArray(IntFunction<long[]> fa,
                                   IntFunction<boolean[]> fm) {
        long[] a = fa.apply(SPECIES.length());
        long[] r = new long[a.length];
        boolean[] mask = fm.apply(SPECIES.length());
        VectorMask<Long> vmask = VectorMask.fromValues(SPECIES, mask);

        for (int ic = 0; ic < INVOC_COUNT; ic++) {
            for (int i = 0; i < a.length; i += SPECIES.length()) {
                LongVector av = LongVector.fromArray(SPECIES, a, i, vmask);
                av.intoArray(r, i);
            }
        }
        assertArraysEquals(a, r, mask);

        r = new long[a.length];
        for (int ic = 0; ic < INVOC_COUNT; ic++) {
            for (int i = 0; i < a.length; i += SPECIES.length()) {
                LongVector av = LongVector.fromArray(SPECIES, a, i);
                av.intoArray(r, i, vmask);
            }
        }

        assertArraysEquals(a, r, mask);
    }

    @Test(dataProvider = "longMaskProvider")
    static void loadStoreMask(IntFunction<long[]> fa,
                              IntFunction<boolean[]> fm) {
        boolean[] mask = fm.apply(SPECIES.length());
        boolean[] r = new boolean[mask.length];

        for (int ic = 0; ic < INVOC_COUNT; ic++) {
            for (int i = 0; i < mask.length; i += SPECIES.length()) {
                VectorMask<Long> vmask = VectorMask.fromArray(SPECIES, mask, i);
                vmask.intoArray(r, i);
            }
        }
        Assert.assertEquals(mask, r);
    }

    @Test(dataProvider = "longByteBufferProvider")
    static void loadStoreByteBuffer(IntFunction<long[]> fa,
                                    IntFunction<ByteBuffer> fb) {
        ByteBuffer a = toBuffer(fa.apply(SPECIES.length()), fb);
        ByteBuffer r = fb.apply(a.limit());

        int l = a.limit();
        int s = SPECIES.length() * SPECIES.elementSize() / 8;

        for (int ic = 0; ic < INVOC_COUNT; ic++) {
            for (int i = 0; i < l; i += s) {
                LongVector av = LongVector.fromByteBuffer(SPECIES, a, i, ByteOrder.LITTLE_ENDIAN);
                av.intoByteBuffer(r, i, ByteOrder.LITTLE_ENDIAN);
            }
        }
        Assert.assertEquals(a.position(), 0, "Input buffer position changed");
        Assert.assertEquals(a.limit(), l, "Input buffer limit changed");
        Assert.assertEquals(r.position(), 0, "Result buffer position changed");
        Assert.assertEquals(r.limit(), l, "Result buffer limit changed");
        Assert.assertEquals(a, r, "Buffers not equal");
    }

    @Test(dataProvider = "longByteBufferProvider")
    static void loadReadOnlyStoreByteBuffer(IntFunction<long[]> fa,
                                            IntFunction<ByteBuffer> fb) {
        ByteBuffer a = toBuffer(fa.apply(SPECIES.length()), fb);
        a = a.asReadOnlyBuffer().order(a.order());
        ByteBuffer r = fb.apply(a.limit());

        int l = a.limit();
        int s = SPECIES.length() * SPECIES.elementSize() / 8;

        for (int ic = 0; ic < INVOC_COUNT; ic++) {
            for (int i = 0; i < l; i += s) {
                LongVector av = LongVector.fromByteBuffer(SPECIES, a, i, ByteOrder.LITTLE_ENDIAN);
                av.intoByteBuffer(r, i, ByteOrder.LITTLE_ENDIAN);
            }
        }
        Assert.assertEquals(a.position(), 0, "Input buffer position changed");
        Assert.assertEquals(a.limit(), l, "Input buffer limit changed");
        Assert.assertEquals(r.position(), 0, "Result buffer position changed");
        Assert.assertEquals(r.limit(), l, "Result buffer limit changed");
        Assert.assertEquals(a, r, "Buffers not equal");
    }

    @Test(dataProvider = "longByteBufferMaskProvider")
    static void loadStoreByteBufferMask(IntFunction<long[]> fa,
                                        IntFunction<ByteBuffer> fb,
                                        IntFunction<boolean[]> fm) {
        ByteBuffer a = toBuffer(fa.apply(SPECIES.length()), fb);
        ByteBuffer r = fb.apply(a.limit());
        boolean[] mask = fm.apply(SPECIES.length());
        VectorMask<Long> vmask = VectorMask.fromValues(SPECIES, mask);

        int l = a.limit();
        int s = SPECIES.length() * SPECIES.elementSize() / 8;

        for (int ic = 0; ic < INVOC_COUNT; ic++) {
            for (int i = 0; i < l; i += s) {
                LongVector av = LongVector.fromByteBuffer(SPECIES, a, i, ByteOrder.LITTLE_ENDIAN, vmask);
                av.intoByteBuffer(r, i, ByteOrder.LITTLE_ENDIAN);
            }
        }
        Assert.assertEquals(a.position(), 0, "Input buffer position changed");
        Assert.assertEquals(a.limit(), l, "Input buffer limit changed");
        Assert.assertEquals(r.position(), 0, "Result buffer position changed");
        Assert.assertEquals(r.limit(), l, "Result buffer limit changed");
        assertArraysEquals(bufferToArray(a), bufferToArray(r), mask);

        a = toBuffer(fa.apply(SPECIES.length()), fb);
        r = fb.apply(a.limit());
        for (int ic = 0; ic < INVOC_COUNT; ic++) {
            for (int i = 0; i < l; i += s) {
                LongVector av = LongVector.fromByteBuffer(SPECIES, a, i, ByteOrder.LITTLE_ENDIAN);
                av.intoByteBuffer(r, i, ByteOrder.LITTLE_ENDIAN, vmask);
            }
        }
        Assert.assertEquals(a.position(), 0, "Input buffer position changed");
        Assert.assertEquals(a.limit(), l, "Input buffer limit changed");
        Assert.assertEquals(r.position(), 0, "Result buffer position changed");
        Assert.assertEquals(r.limit(), l, "Result buffer limit changed");
        assertArraysEquals(bufferToArray(a), bufferToArray(r), mask);
    }

    @Test(dataProvider = "longByteBufferMaskProvider")
    static void loadReadOnlyStoreByteBufferMask(IntFunction<long[]> fa,
                                                IntFunction<ByteBuffer> fb,
                                                IntFunction<boolean[]> fm) {
        ByteBuffer a = toBuffer(fa.apply(SPECIES.length()), fb);
        a = a.asReadOnlyBuffer().order(a.order());
        ByteBuffer r = fb.apply(a.limit());
        boolean[] mask = fm.apply(SPECIES.length());
        VectorMask<Long> vmask = VectorMask.fromValues(SPECIES, mask);

        int l = a.limit();
        int s = SPECIES.length() * SPECIES.elementSize() / 8;

        for (int ic = 0; ic < INVOC_COUNT; ic++) {
            for (int i = 0; i < l; i += s) {
                LongVector av = LongVector.fromByteBuffer(SPECIES, a, i, ByteOrder.LITTLE_ENDIAN, vmask);
                av.intoByteBuffer(r, i, ByteOrder.LITTLE_ENDIAN);
            }
        }
        Assert.assertEquals(a.position(), 0, "Input buffer position changed");
        Assert.assertEquals(a.limit(), l, "Input buffer limit changed");
        Assert.assertEquals(r.position(), 0, "Result buffer position changed");
        Assert.assertEquals(r.limit(), l, "Result buffer limit changed");
        assertArraysEquals(bufferToArray(a), bufferToArray(r), mask);
    }

    @Test(dataProvider = "longByteArrayProvider")
    static void loadStoreByteArray(IntFunction<long[]> fa,
                                    IntFunction<byte[]> fb) {
        byte[] a = toByteArray(fa.apply(SPECIES.length()), fb, ByteOrder.LITTLE_ENDIAN);
        byte[] r = fb.apply(a.length);

        int s = SPECIES.length() * SPECIES.elementSize() / 8;
        int l = a.length;

        for (int ic = 0; ic < INVOC_COUNT; ic++) {
            for (int i = 0; i < l; i += s) {
                LongVector av = LongVector.fromByteArray(SPECIES, a, i, ByteOrder.LITTLE_ENDIAN);
                av.intoByteArray(r, i);
            }
        }
        Assert.assertEquals(a, r, "Byte arrays not equal");
    }

    @Test(dataProvider = "longByteArrayMaskProvider")
    static void loadByteArrayMask(IntFunction<long[]> fa,
                                  IntFunction<byte[]> fb,
                                  IntFunction<boolean[]> fm) {
          byte[] a = toByteArray(fa.apply(SPECIES.length()), fb, ByteOrder.LITTLE_ENDIAN);
          byte[] r = fb.apply(a.length);
          boolean[] mask = fm.apply(SPECIES.length());
          VectorMask<Long> vmask = VectorMask.fromValues(SPECIES, mask);

          int s = SPECIES.length() * SPECIES.elementSize() / 8;
          int l = a.length;

          for (int ic = 0; ic < INVOC_COUNT; ic++) {
              for (int i = 0; i < l; i += s) {
                  LongVector av = LongVector.fromByteArray(SPECIES, a, i, ByteOrder.LITTLE_ENDIAN, vmask);
                  av.intoByteArray(r, i);
              }
          }
          assertArraysEquals(a, r, mask);
    }

    @Test(dataProvider = "longByteArrayMaskProvider")
    static void storeByteArrayMask(IntFunction<long[]> fa,
                                   IntFunction<byte[]> fb,
                                   IntFunction<boolean[]> fm) {
        byte[] a = toByteArray(fa.apply(SPECIES.length()), fb, ByteOrder.LITTLE_ENDIAN);
        byte[] r = fb.apply(a.length);
        boolean[] mask = fm.apply(SPECIES.length());
        VectorMask<Long> vmask = VectorMask.fromValues(SPECIES, mask);

        int s = SPECIES.length() * SPECIES.elementSize() / 8;
        int l = a.length;

        a = toByteArray(fa.apply(SPECIES.length()), fb, ByteOrder.LITTLE_ENDIAN);
        r = fb.apply(a.length);
        for (int ic = 0; ic < INVOC_COUNT; ic++) {
            for (int i = 0; i < l; i += s) {
                LongVector av = LongVector.fromByteArray(SPECIES, a, i, ByteOrder.LITTLE_ENDIAN);
                av.intoByteArray(r, i, ByteOrder.LITTLE_ENDIAN, vmask);
            }
        }
        assertArraysEquals(a, r, mask);
    }
}
