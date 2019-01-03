package com.company.RemoteExecution;

/**
 * 第二个类是实现将lang.System替换为我们自己定义
 * 的HackSystem类的过程,它直接修改符合Class文件
 * 格式的byte[]的常量池的部分(将CONSTANT_Utf8_info替换为新的字符串)
 *
 * 修改Class文件,暂时只是提供修改常量池量的功能
 *
 * @author lc
 */
public class ClassModifier {
    /**
     * Class文件中常量池的偏移量
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    /**
     * CONSTANT_Utf8_info常量的tag标志
     */
    private static final int CONSTANT_Utf8_info = 1;

    /**
     * 常量池中11种常量所占的长度,CONSTANT_Utf8_info型常量除外,因为是不定长的
     */
    private static final int[] COSNTANT_ITEM_LENGTH = {-1,-1,-1,5,5,9,9,3,3,5,5,5,5};

    private static final int u1 = 1;
    private static final int u2 = 2;

    private byte[] classByte;

    public ClassModifier(byte[] classByte) {
        this.classByte = classByte;
    }

    /**
     * 修改常量池CONSTANT_Utf8_info常量的内容
     * @param oldStr 之前的字符串
     * @param newStr 之后的字符串
     * @return 修改结果
     */
    public byte[] modifyUTF8Contant(String oldStr,String newStr){
        int cpc = getConstantPoolCount();
        int offset = CONSTANT_POOL_COUNT_INDEX+u2;
        for (int i = 0; i < cpc; i++) {
            int tag = ByteUtils.bytes2Int(classByte,offset,u1);
            if ( tag == CONSTANT_Utf8_info){
                int len = ByteUtils.bytes2Int(classByte,offset+u1,u2);
                offset+=(u1+u2);
                String str = ByteUtils.bytes2String(classByte,offset,len);
                if(str.equalsIgnoreCase(oldStr)){
                    byte[] strBytes = ByteUtils.string2Bytes(newStr);
                    byte[] strLen = ByteUtils.int2Byte2(newStr.length(),u2);
                    classByte = ByteUtils.bytesReplace(classByte,offset-u2,u2,strLen);
                    classByte = ByteUtils.bytesReplace(classByte,offset,len,strBytes);
                    return classByte;
                }else{
                    offset +=len;
                }
            }else{
                offset +=COSNTANT_ITEM_LENGTH[tag];
            }
        }
        return classByte;
    }

    /**
     * 获取常量池中的数量
     * @return 常量池数量
     */
    public int getConstantPoolCount(){
        return ByteUtils.bytes2Int(classByte,CONSTANT_POOL_COUNT_INDEX,u2);
    }
}
/**
 * 经过ClassModifier处理后的byte[]数组才会传给HotSwapClassLoader.loadByte()
 * 方法进行类加载,byte[]数组在这里替换符号引用之后,与客户端直接在JAVA代码中引用
 * HackSystem类在编译生成的Class是完全一样的.这样既避免了修改标准输出后的
 * 影响又避免了客户端临时执行代码依赖特定类
 */
