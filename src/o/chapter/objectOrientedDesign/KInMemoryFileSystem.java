package o.chapter.objectOrientedDesign;

import jdk.nashorn.internal.ir.Block;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KInMemoryFileSystem {
    public static void main(String[] args) {
        FileSystem customFileSystem = new FileSystem();
        List<MetaData> root = customFileSystem.getRoot();
        System.out.println(root.get(0).getAttribute()[ 1 ].getName());
    }
}

class FileSystem {
    /* File Allocation System */
    /* It will contain a list of directories and the structure will go on like a tree structure
     * Each Metadata will have Attributes and for the Directory and File we will be having a block
     * array mentioning the reference of blocks it is occupying and if the file size increases
     * we look for a new block which is unallocated and then add it to the block.
     * When the file is moved from one folder structure to another we will simply remove the metadata from the
     * fromFolder to the toFolder and so there is not much copying that needs to be made */

    /* File De-allocation System */
    /* In a similar way we had a mechanism to identify the free blocks there will be a mechanism to identify
     * freed up blocks */

    private List<Block> unallocatedBlocks;
    private List<MetaData> root;

    public List<Block> getUnallocatedBlocks() {
        return unallocatedBlocks;
    }

    public void setUnallocatedBlocks(List<Block> unallocatedBlocks) {
        this.unallocatedBlocks = unallocatedBlocks;
    }

    public List<MetaData> getRoot() {
        return root;
    }

    public void setRoot(List<MetaData> root) {
        this.root = root;
    }
}

class Directory {
    private int id;
    private String name;
    private MetaData metaData;
    private Map<Integer, MetaData> contents;

    public Directory(int id, String name, MetaData metaData) {
        this.id = id;
        this.name = name;
        this.metaData = metaData;
        this.contents = new HashMap<Integer, MetaData>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Map<Integer, MetaData> getContents() {
        return contents;
    }

    public void setContents(Map<Integer, MetaData> contents) {
        this.contents = contents;
    }
}

class File {
    private int id;
    private String name;
    private MetaData metaData;
    private Data data;

    public File(int id, String name, MetaData metaData, Data data) {
        this.id = id;
        this.name = name;
        this.metaData = metaData;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}

class Data {
    private Object[] data;

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }
}

class MetaData {
    private enum Type {WAV, MP3, MP4, MPEG, TXT, PSD, PPT, PPTX, DIR}

    private Type type;
    private Attribute[] attribute;

    public MetaData(Type type, Attribute[] attribute) {
        this.type = type;
        this.attribute = attribute;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Attribute[] getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute[] attribute) {
        this.attribute = attribute;
    }
}

class Attribute {
    private int id;
    private int name;
    private int size;

    public Attribute(int id, int name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

class SoundAttributes extends Attribute {
    private int sampleRate;
    private int amplitude;
    private int frequency;
    private int bitRate;

    public SoundAttributes(int id, int name, int size, int sampleRate, int amplitude, int frequency, int bitRate) {
        super(id, name, size);
        this.sampleRate = sampleRate;
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.bitRate = bitRate;
    }
}

class TextAttributes extends Attribute {
    private enum Standard {ASCII, MIME, UTF8, UTF16}

    private Standard standard;
    private int textSize;
    private int spacing;

    public TextAttributes(int id, int name, int size, Standard standard, int textSize, int spacing) {
        super(id, name, size);
        this.standard = standard;
        this.textSize = textSize;
        this.spacing = spacing;
    }
}

class ImageAttributes extends Attribute {
    private int width;
    private int height;
    private int pixelsPerRow;

    public ImageAttributes(int id, int name, int size, int width, int height, int pixelsPerRow) {
        super(id, name, size);
        this.width = width;
        this.height = height;
        this.pixelsPerRow = pixelsPerRow;
    }
}

class DirectoryFileAttributes extends Attribute {
    private String dirOrFileName;
    private boolean directory;
    private Date createdDate;
    private Date lastmodifiedDate;
    private Block[] blocks;

    public DirectoryFileAttributes(int id, int name, int size, String dirOrFileName, boolean directory, Date createdDate, Date lastmodifiedDate, Block[] blocks) {
        super(id, name, size);
        this.dirOrFileName = dirOrFileName;
        this.directory = directory;
        this.createdDate = createdDate;
        this.lastmodifiedDate = lastmodifiedDate;
        this.blocks = blocks;
    }
}