package com.dandy.core;

public class Contact {
    private int contactId;
    private String description;
    private long number;

    public Contact() {}

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setNumber(long number) {
        this.number = number;
    }
    public int getContactId() {
        return this.contactId;
    }
    public String getDescription() {
        return this.description;
    }
    public long getNumber() {
        return this.number;
    }

    public boolean equals(Object obj) {
      if (obj == null) return false;
      if (!this.getClass().equals(obj.getClass())) return false;

      Contact obj2 = (Contact)obj;
      if((this.number == obj2.getNumber()) && (this.description.equals(obj2.getDescription())))
      {
         return true;
      }
      return false;
   }

   public int hashCode() {
      int tmp = 0;
      tmp = ( number + description ).hashCode();
      return tmp;
   }
}
