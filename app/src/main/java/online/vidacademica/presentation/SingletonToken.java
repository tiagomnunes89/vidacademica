package online.vidacademica.presentation;

import online.vidacademica.entities.TokenEntity;

public enum SingletonToken {
    
    INSTANCE;

    private TokenEntity tokenEntity;

    private void setTokenEntity(TokenEntity tokenEntity) {
        this.tokenEntity = tokenEntity;
    }

    public static void createTokenEntity(TokenEntity tokenEntity) {
        SingletonToken.INSTANCE.setTokenEntity(tokenEntity);
    }

    public TokenEntity getTokenEntity() {
        if (tokenEntity != null) {
            return tokenEntity;
        }
        return null;
    }
}
