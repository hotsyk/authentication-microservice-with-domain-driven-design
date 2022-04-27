package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.BaseMapper;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionRepository;
import com.tomo.mcauthentication.domain.session.TokenProvider;

import org.modelmapper.ModelMapper;

public class BaseLoginCommandHandler extends BaseMapper {
    protected SessionRepository sessionRepository;
    protected TokenProvider tokenProvider;

    public BaseLoginCommandHandler() {
    }

    public BaseLoginCommandHandler(ModelMapper modelMapper, TokenProvider tokenProvider) {
        super(modelMapper);
        this.tokenProvider = tokenProvider;
    }

    public BaseLoginCommandHandler(
            ModelMapper modelMapper,
            SessionRepository sessionRepository,
            TokenProvider tokenProvider) {
        super(modelMapper);
        this.sessionRepository = sessionRepository;
        this.tokenProvider = tokenProvider;
    }

    protected SessionDto toDto(Session session) {
        return SessionDto.create(session, this.modelMapper);
    }
}
