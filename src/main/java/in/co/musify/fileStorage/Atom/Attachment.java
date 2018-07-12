package in.co.musify.fileStorage.Atom;

/**
 * Created by aparna.n on 23/01/16.
 */
public class Attachment {
    private String filePath;
    private String hfdsPath;

    private Attachment(String filePath, String hfdsPath) {
        this.filePath = filePath;
        this.hfdsPath = hfdsPath;
    }

    public static class AttachmentBuilder {
        private String filePath;
        private String hfdsPath;


        public AttachmentBuilder withFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public AttachmentBuilder withHdfsPath(String hdfsPath) {
            this.hfdsPath = hdfsPath;
            return this;
        }

        public Attachment createAttachment() {
            return new Attachment(filePath, hfdsPath);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public String getHfdsPath() {
        return hfdsPath;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "filePath='" + filePath + '\'' +
                ", hdfsPath='" + hfdsPath + '\'' +
                "}";
    }

}

