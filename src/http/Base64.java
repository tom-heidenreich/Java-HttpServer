package http;

public class Base64 {
    private static final char[] intToBase64 = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final char[] intToAltBase64 = new char[]{'!', '\"', '#', '$', '%', '&', '\'', '(', ')', ',', '-', '.', ':', ';', '<', '>', '@', '[', ']', '^', '`', '_', '{', '|', '}', '~', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '?'};
    private static final byte[] base64ToInt;
    private static final byte[] altBase64ToInt;

    static {
        byte[] arrby = new byte[123];
        arrby[0] = -1;
        arrby[1] = -1;
        arrby[2] = -1;
        arrby[3] = -1;
        arrby[4] = -1;
        arrby[5] = -1;
        arrby[6] = -1;
        arrby[7] = -1;
        arrby[8] = -1;
        arrby[9] = -1;
        arrby[10] = -1;
        arrby[11] = -1;
        arrby[12] = -1;
        arrby[13] = -1;
        arrby[14] = -1;
        arrby[15] = -1;
        arrby[16] = -1;
        arrby[17] = -1;
        arrby[18] = -1;
        arrby[19] = -1;
        arrby[20] = -1;
        arrby[21] = -1;
        arrby[22] = -1;
        arrby[23] = -1;
        arrby[24] = -1;
        arrby[25] = -1;
        arrby[26] = -1;
        arrby[27] = -1;
        arrby[28] = -1;
        arrby[29] = -1;
        arrby[30] = -1;
        arrby[31] = -1;
        arrby[32] = -1;
        arrby[33] = -1;
        arrby[34] = -1;
        arrby[35] = -1;
        arrby[36] = -1;
        arrby[37] = -1;
        arrby[38] = -1;
        arrby[39] = -1;
        arrby[40] = -1;
        arrby[41] = -1;
        arrby[42] = -1;
        arrby[43] = 62;
        arrby[44] = -1;
        arrby[45] = -1;
        arrby[46] = -1;
        arrby[47] = 63;
        arrby[48] = 52;
        arrby[49] = 53;
        arrby[50] = 54;
        arrby[51] = 55;
        arrby[52] = 56;
        arrby[53] = 57;
        arrby[54] = 58;
        arrby[55] = 59;
        arrby[56] = 60;
        arrby[57] = 61;
        arrby[58] = -1;
        arrby[59] = -1;
        arrby[60] = -1;
        arrby[61] = -1;
        arrby[62] = -1;
        arrby[63] = -1;
        arrby[64] = -1;
        arrby[66] = 1;
        arrby[67] = 2;
        arrby[68] = 3;
        arrby[69] = 4;
        arrby[70] = 5;
        arrby[71] = 6;
        arrby[72] = 7;
        arrby[73] = 8;
        arrby[74] = 9;
        arrby[75] = 10;
        arrby[76] = 11;
        arrby[77] = 12;
        arrby[78] = 13;
        arrby[79] = 14;
        arrby[80] = 15;
        arrby[81] = 16;
        arrby[82] = 17;
        arrby[83] = 18;
        arrby[84] = 19;
        arrby[85] = 20;
        arrby[86] = 21;
        arrby[87] = 22;
        arrby[88] = 23;
        arrby[89] = 24;
        arrby[90] = 25;
        arrby[91] = -1;
        arrby[92] = -1;
        arrby[93] = -1;
        arrby[94] = -1;
        arrby[95] = -1;
        arrby[96] = -1;
        arrby[97] = 26;
        arrby[98] = 27;
        arrby[99] = 28;
        arrby[100] = 29;
        arrby[101] = 30;
        arrby[102] = 31;
        arrby[103] = 32;
        arrby[104] = 33;
        arrby[105] = 34;
        arrby[106] = 35;
        arrby[107] = 36;
        arrby[108] = 37;
        arrby[109] = 38;
        arrby[110] = 39;
        arrby[111] = 40;
        arrby[112] = 41;
        arrby[113] = 42;
        arrby[114] = 43;
        arrby[115] = 44;
        arrby[116] = 45;
        arrby[117] = 46;
        arrby[118] = 47;
        arrby[119] = 48;
        arrby[120] = 49;
        arrby[121] = 50;
        arrby[122] = 51;
        base64ToInt = arrby;
        byte[] arrby2 = new byte[127];
        arrby2[0] = -1;
        arrby2[1] = -1;
        arrby2[2] = -1;
        arrby2[3] = -1;
        arrby2[4] = -1;
        arrby2[5] = -1;
        arrby2[6] = -1;
        arrby2[7] = -1;
        arrby2[8] = -1;
        arrby2[9] = -1;
        arrby2[10] = -1;
        arrby2[11] = -1;
        arrby2[12] = -1;
        arrby2[13] = -1;
        arrby2[14] = -1;
        arrby2[15] = -1;
        arrby2[16] = -1;
        arrby2[17] = -1;
        arrby2[18] = -1;
        arrby2[19] = -1;
        arrby2[20] = -1;
        arrby2[21] = -1;
        arrby2[22] = -1;
        arrby2[23] = -1;
        arrby2[24] = -1;
        arrby2[25] = -1;
        arrby2[26] = -1;
        arrby2[27] = -1;
        arrby2[28] = -1;
        arrby2[29] = -1;
        arrby2[30] = -1;
        arrby2[31] = -1;
        arrby2[32] = -1;
        arrby2[34] = 1;
        arrby2[35] = 2;
        arrby2[36] = 3;
        arrby2[37] = 4;
        arrby2[38] = 5;
        arrby2[39] = 6;
        arrby2[40] = 7;
        arrby2[41] = 8;
        arrby2[42] = -1;
        arrby2[43] = 62;
        arrby2[44] = 9;
        arrby2[45] = 10;
        arrby2[46] = 11;
        arrby2[47] = -1;
        arrby2[48] = 52;
        arrby2[49] = 53;
        arrby2[50] = 54;
        arrby2[51] = 55;
        arrby2[52] = 56;
        arrby2[53] = 57;
        arrby2[54] = 58;
        arrby2[55] = 59;
        arrby2[56] = 60;
        arrby2[57] = 61;
        arrby2[58] = 12;
        arrby2[59] = 13;
        arrby2[60] = 14;
        arrby2[61] = -1;
        arrby2[62] = 15;
        arrby2[63] = 63;
        arrby2[64] = 16;
        arrby2[65] = -1;
        arrby2[66] = -1;
        arrby2[67] = -1;
        arrby2[68] = -1;
        arrby2[69] = -1;
        arrby2[70] = -1;
        arrby2[71] = -1;
        arrby2[72] = -1;
        arrby2[73] = -1;
        arrby2[74] = -1;
        arrby2[75] = -1;
        arrby2[76] = -1;
        arrby2[77] = -1;
        arrby2[78] = -1;
        arrby2[79] = -1;
        arrby2[80] = -1;
        arrby2[81] = -1;
        arrby2[82] = -1;
        arrby2[83] = -1;
        arrby2[84] = -1;
        arrby2[85] = -1;
        arrby2[86] = -1;
        arrby2[87] = -1;
        arrby2[88] = -1;
        arrby2[89] = -1;
        arrby2[90] = -1;
        arrby2[91] = 17;
        arrby2[92] = -1;
        arrby2[93] = 18;
        arrby2[94] = 19;
        arrby2[95] = 21;
        arrby2[96] = 20;
        arrby2[97] = 26;
        arrby2[98] = 27;
        arrby2[99] = 28;
        arrby2[100] = 29;
        arrby2[101] = 30;
        arrby2[102] = 31;
        arrby2[103] = 32;
        arrby2[104] = 33;
        arrby2[105] = 34;
        arrby2[106] = 35;
        arrby2[107] = 36;
        arrby2[108] = 37;
        arrby2[109] = 38;
        arrby2[110] = 39;
        arrby2[111] = 40;
        arrby2[112] = 41;
        arrby2[113] = 42;
        arrby2[114] = 43;
        arrby2[115] = 44;
        arrby2[116] = 45;
        arrby2[117] = 46;
        arrby2[118] = 47;
        arrby2[119] = 48;
        arrby2[120] = 49;
        arrby2[121] = 50;
        arrby2[122] = 51;
        arrby2[123] = 22;
        arrby2[124] = 23;
        arrby2[125] = 24;
        arrby2[126] = 25;
        altBase64ToInt = arrby2;
    }

    Base64() {
    }

    static String byteArrayToBase64(byte[] a) {
        return Base64.byteArrayToBase64(a, false);
    }

    static String byteArrayToAltBase64(byte[] a) {
        return Base64.byteArrayToBase64(a, true);
    }

    private static String byteArrayToBase64(byte[] a, boolean alternate) {
        int aLen = a.length;
        int numFullGroups = aLen / 3;
        int numBytesInPartialGroup = aLen - 3 * numFullGroups;
        int resultLen = 4 * ((aLen + 2) / 3);
        StringBuffer result = new StringBuffer(resultLen);
        char[] intToAlpha = alternate ? intToAltBase64 : intToBase64;
        int inCursor = 0;
        for (int i = 0; i < numFullGroups; ++i) {
            int byte0 = a[inCursor++] & 255;
            int byte1 = a[inCursor++] & 255;
            int byte2 = a[inCursor++] & 255;
            result.append(intToAlpha[byte0 >> 2]);
            result.append(intToAlpha[byte0 << 4 & 63 | byte1 >> 4]);
            result.append(intToAlpha[byte1 << 2 & 63 | byte2 >> 6]);
            result.append(intToAlpha[byte2 & 63]);
        }
        if (numBytesInPartialGroup != 0) {
            int byte0 = a[inCursor++] & 255;
            result.append(intToAlpha[byte0 >> 2]);
            if (numBytesInPartialGroup == 1) {
                result.append(intToAlpha[byte0 << 4 & 63]);
                result.append("==");
            } else {
                int byte1 = a[inCursor++] & 255;
                result.append(intToAlpha[byte0 << 4 & 63 | byte1 >> 4]);
                result.append(intToAlpha[byte1 << 2 & 63]);
                result.append('=');
            }
        }
        return result.toString();
    }

    public static byte[] base64ToByteArray(String s) {
        return Base64.base64ToByteArray(s, false);
    }

    static byte[] altBase64ToByteArray(String s) {
        return Base64.base64ToByteArray(s, true);
    }

    private static byte[] base64ToByteArray(String s, boolean alternate) {
        byte[] alphaToInt = alternate ? altBase64ToInt : base64ToInt;
        int sLen = s.length();
        int numGroups = sLen / 4;
        if (4 * numGroups != sLen) {
            throw new IllegalArgumentException("String length must be a multiple of four.");
        }
        int missingBytesInLastGroup = 0;
        int numFullGroups = numGroups;
        if (sLen != 0) {
            if (s.charAt(sLen - 1) == '=') {
                ++missingBytesInLastGroup;
                --numFullGroups;
            }
            if (s.charAt(sLen - 2) == '=') {
                ++missingBytesInLastGroup;
            }
        }
        byte[] result = new byte[3 * numGroups - missingBytesInLastGroup];
        int inCursor = 0;
        int outCursor = 0;
        for (int i = 0; i < numFullGroups; ++i) {
            int ch0 = Base64.base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch1 = Base64.base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch2 = Base64.base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch3 = Base64.base64toInt(s.charAt(inCursor++), alphaToInt);
            result[outCursor++] = (byte)(ch0 << 2 | ch1 >> 4);
            result[outCursor++] = (byte)(ch1 << 4 | ch2 >> 2);
            result[outCursor++] = (byte)(ch2 << 6 | ch3);
        }
        if (missingBytesInLastGroup != 0) {
            int ch0 = Base64.base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch1 = Base64.base64toInt(s.charAt(inCursor++), alphaToInt);
            result[outCursor++] = (byte)(ch0 << 2 | ch1 >> 4);
            if (missingBytesInLastGroup == 1) {
                int ch2 = Base64.base64toInt(s.charAt(inCursor++), alphaToInt);
                result[outCursor++] = (byte)(ch1 << 4 | ch2 >> 2);
            }
        }
        return result;
    }

    private static int base64toInt(char c, byte[] alphaToInt) {
        byte result = alphaToInt[c];
        if (result < 0) {
            throw new IllegalArgumentException("Illegal character " + c);
        }
        return result;
    }
}

