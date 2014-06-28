/**
 * Sizeof.java
 * com.qagwaai.starmalaccamax.server.util
 * StarMalaccamax
 * Created Apr 22, 2011 at 1:46:23 PM
 */
package com.qagwaai.starmalaccamax.server.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author pgirard
 * 
 */
public final class Sizeof {
    
    private static final int SZ_REF = 4;
    
    private Sizeof() { }

    public static int sizeof(final boolean b) {
        return 1;
    }

    public static int sizeof(final byte b) {
        return 1;
    }

    public static int sizeof(final char c) {
        return 2;
    }

    public static int sizeof(final short s) {
        return 2;
    }

    public static int sizeof(final int i) {
        return 4;
    }

    public static int sizeof(final long l) {
        return 8;
    }

    public static int sizeof(final float f) {
        return 4;
    }

    public static int sizeof(final double d) {
        return 8;
    }

    private static int size_inst(final Object obj, final Class c) {
        Field[] flds = c.getDeclaredFields();
        int sz = 0;

        for (int i = 0; i < flds.length; i++) {
            Field f = flds[i];
            if (!c.isInterface() && (f.getModifiers() & Modifier.STATIC) != 0) {
                continue;
            }
            if (c.isPrimitive()) {
                sz += size_prim(f.getType());
            } else {
                Object subObj = null;
                try {
                    subObj = f.get(obj);
                } catch (Throwable t) {
                    System.out.println("caught exception: " + t.getMessage());
                }
                if (subObj != null) {
                    @SuppressWarnings("rawtypes")
                    Class subClass = subObj.getClass();
                    if (subObj.getClass().isArray()) {
                        sz += size_arr(subObj, subClass);
                    } else {
                        sz += sizeof(subObj);
                    }

                }
            }
        }

        if (c.getSuperclass() != null) {
            sz += size_inst(obj, c.getSuperclass());
        }

        @SuppressWarnings("rawtypes")
        Class[] cv = c.getInterfaces();
        for (int i = 0; i < cv.length; i++) {
            sz += size_inst(obj, cv[i]);
        }

        return sz;
    }

    private static int size_prim(Class t) {
        if (t == Boolean.TYPE) {
            return 1;
        } else if (t == Byte.TYPE) {
            return 1;
        } else if (t == Character.TYPE) {
            return 2;
        } else if (t == Short.TYPE) {
            return 2;
        } else if (t == Integer.TYPE) {
            return 4;
        } else if (t == Long.TYPE) {
            return 8;
        } else if (t == Float.TYPE) {
            return 4;
        } else if (t == Double.TYPE) {
            return 8;
        } else if (t == Void.TYPE) {
            return 0;
        } else {
            return SZ_REF;
        }
    }

    private static int size_arr(Object obj, Class c) {
        Class ct = c.getComponentType();
        int len = Array.getLength(obj);

        if (ct.isPrimitive()) {
            return len * size_prim(ct);
        } else {
            int sz = 0;
            for (int i = 0; i < len; i++) {
                sz += SZ_REF;
                Object obj2 = Array.get(obj, i);
                if (obj2 == null) {
                    continue;
                }
                Class c2 = obj2.getClass();
                if (!c2.isArray()) {
                    continue;
                }
                sz += size_arr(obj2, c2);
            }
            return sz;
        }
    }

    public static int sizeof(Object obj) {
        if (obj == null) {
            return 0;
        }

        Class c = obj.getClass();

        if (c.isArray()) {
            return size_arr(obj, c);
        } else {
            return size_inst(obj, c);
        }
    }

}