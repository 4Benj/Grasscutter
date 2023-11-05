package emu.grasscutter.server.packet.send;

import com.google.protobuf.ByteString;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.packet.response.GetPlayerTokenResponse;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.utils.Crypto;

public class PacketGetPlayerTokenRsp extends BasePacket {

    public PacketGetPlayerTokenRsp(GameSession session) {
        super(session.getVersion().GetOperationCode(PacketOpcodes.GetPlayerTokenRsp), true);

        this.setUseDispatchKey(true);

        GetPlayerTokenResponse p =
                new GetPlayerTokenResponse()
                        .setUid(session.getPlayer().getUid())
                        .setToken(session.getAccount().getToken())
                        .setAccountType(1)
                        .setIsProficientPlayer(
                                session.getPlayer().getAvatars().getAvatarCount() > 0) // Not sure where this goes
                        .setSecretKeySeed(session.getEncryptSeed())
                        .setSecurityCmdBuffer(ByteString.copyFrom(Crypto.ENCRYPT_SEED_BUFFER))
                        .setPlatformType(3)
                        .setChannelId(1)
                        .setCountryCode("US")
                        .setClientVersionRandomKey("c25-314dd05b0b5f")
                        .setRegPlatform(3)
                        .setClientIpStr(session.getAddress().getAddress().getHostAddress());

        this.setData(p.Serialize(session.getVersion()).toByteArray());
    }

    public PacketGetPlayerTokenRsp(GameSession session, int retcode, String msg, int blackEndTime) {
        super(session.getVersion().GetOperationCode(PacketOpcodes.GetPlayerTokenRsp), true);

        this.setUseDispatchKey(true);

        GetPlayerTokenResponse p =
                new GetPlayerTokenResponse()
                        .setUid(session.getPlayer().getUid())
                        .setIsProficientPlayer(session.getPlayer().getAvatars().getAvatarCount() > 0)
                        .setRetcode(retcode)
                        .setMsg(msg)
                        .setBlackUidEndTime(blackEndTime)
                        .setRegPlatform(3)
                        .setCountryCode("US")
                        .setClientIpStr(session.getAddress().getAddress().getHostAddress());

        this.setData(p.Serialize(session.getVersion()).toByteArray());
    }

    public PacketGetPlayerTokenRsp(
            GameSession session, String encryptedSeed, String encryptedSeedSign) {
        super(session.getVersion().GetOperationCode(PacketOpcodes.GetPlayerTokenRsp), true);

        this.setUseDispatchKey(true);

        GetPlayerTokenResponse p =
                new GetPlayerTokenResponse()
                        .setUid(session.getPlayer().getUid())
                        .setToken(session.getAccount().getToken())
                        .setAccountType(1)
                        .setIsProficientPlayer(
                                session.getPlayer().getAvatars().getAvatarCount() > 0) // Not sure where this goes
                        .setSecretKeySeed(session.getEncryptSeed())
                        .setSecurityCmdBuffer(ByteString.copyFrom(Crypto.ENCRYPT_SEED_BUFFER))
                        .setPlatformType(3)
                        .setChannelId(1)
                        .setCountryCode("US")
                        .setClientVersionRandomKey("c25-314dd05b0b5f")
                        .setRegPlatform(3)
                        .setClientIpStr(session.getAddress().getAddress().getHostAddress())
                        .setServerRandKey(encryptedSeed)
                        .setSign(encryptedSeedSign);

        this.setData(p.Serialize(session.getVersion()).toByteArray());
    }
}
