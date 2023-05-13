package local.rps.tag_recepta;

public class TagReceptaDto {
	private int tagId;
	private int receptId;
	
	public TagReceptaDto(int tagId, int receptId) {
		super();
		this.tagId = tagId;
		this.receptId = receptId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getReceptId() {
		return receptId;
	}

	public void setReceptId(int receptId) {
		this.receptId = receptId;
	}
	
	
}
