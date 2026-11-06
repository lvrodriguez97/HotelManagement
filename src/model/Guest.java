package model;

public class Guest {
    
    private final int id;
    private final String fullName;
    private final String document;
    private final String phone;
    private final String email;
    
    Guest(int newId, String newFullName, String newDocument, String newPhone, String newEmail){
        id = newId;

        validateNotBlank(newFullName, "nombre");
        for(char c : newFullName.toCharArray()){
            if(!Character.isLetter(c) && !(c == ' ')){
                throw new IllegalArgumentException("No se admite el carácter '"+c+"' en el nombre");
            }
        }
        fullName = newFullName;

        validateNotBlank(newDocument, "documento");
        for(char c : newDocument.toCharArray()){
            if(!Character.isDigit(c)){
                throw new IllegalArgumentException("El documento solo admite números, '"+c+"' no permitido");
            }
        }
        document = newDocument;

        validateNotBlank(newPhone, "teléfono");
        for(char c : newPhone.toCharArray()){
            if(!Character.isDigit(c)){
                throw new IllegalArgumentException("El teléfono de contacto solo admite números, '"+c+"' no permitido");
            }
        }
        phone = newPhone;

        validateNotBlank(newEmail, "correo electrónico");
        if(!(newEmail.contains("@"))){
            throw new IllegalArgumentException("El correo debe tener '@' para ser válido");
        }
        if((newEmail.contains(" "))){
            throw new IllegalArgumentException("El correo no puede tener espacios");
        }
        email = newEmail;
    }

    private void validateNotBlank(String value, String fieldName){
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException("El "+fieldName+" no puede estar en blanco");
        }
    }

    public int getId(){
        return id;
    }

    public String getFullName(){
        return fullName;
    }

    public String getDocument(){
        return document;
    }

    public String getPhone(){
        return phone;
    }

    public String getEmail(){
        return email;
    }
}
