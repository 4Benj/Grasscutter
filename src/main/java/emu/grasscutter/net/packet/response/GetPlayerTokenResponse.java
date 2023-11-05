package emu.grasscutter.net.packet.response;

import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.packet.ProtocolMessage;
import emu.grasscutter.net.packet.general.StopServerInfo;
import emu.grasscutter.server.game.version.GameVersion;

import java.util.List;

public class GetPlayerTokenResponse extends ProtocolMessage {
    private ByteString securityCmdBuffer;
    private int gmUid;
    private boolean isGuest;
    private boolean unk3300Ajbbipfmbel;
    private int keyId;
    private String sign;
    private String secretKey;
    private int accountType;
    private int channelId;
    private byte[] extraBinData;
    private long secretKeySeed;
    private String token;
    private String serverRandKey;
    private int cloudClientIp;
    private String birthday;
    private int tag;
    private String clientIpStr;
    private int subChannelId;
    private int retcode;
    private String clientVersionRandomKey;
    private String psnId;
    private boolean isProficientPlayer;
    private int platformType;
    private int regPlatform;
    private StopServerInfo stopServer;
    private int blackUidEndTime;
    private boolean unk3300Cfbhahohdkc;
    private String accountUid;
    private List<Integer> finishCollectionIdList;
    private String msg;
    private String countryCode;
    private int uid;

    @Override
    public Message Serialize(GameVersion gameVersion) {
        throw new UnsupportedOperationException("This packet cannot be serialized");
    }

    public GetPlayerTokenResponse Deserialize(GameVersion gameVersion, Message message) {
        if (message == null) {
            // Handle the case where the message is null or incompatible.
            return null;
        }

        if (!message.getDescriptorForType().getFullName().equals(
                gameVersion.GetMessage(PacketOpcodes.GetPlayerTokenRsp).toBuilder().getDescriptorForType().getFullName())) {
            // Handle the case where the received message type doesn't match the expected type.
            return null;
        }

        GetPlayerTokenResponse getPlayerTokenResponse = new GetPlayerTokenResponse();

        Descriptors.Descriptor descriptor = message.getDescriptorForType();

        getPlayerTokenResponse.securityCmdBuffer = (ByteString) getField(message, descriptor, "security_cmd_buffer");
        getPlayerTokenResponse.gmUid = (int) getField(message, descriptor, "gm_uid");
        getPlayerTokenResponse.isGuest = (boolean) getField(message, descriptor, "is_guest");
        getPlayerTokenResponse.unk3300Ajbbipfmbel = (boolean) getField(message, descriptor, "Unk3300_AJBBIPFMBEL");
        getPlayerTokenResponse.keyId = (int) getField(message, descriptor, "key_id");
        getPlayerTokenResponse.sign = (String) getField(message, descriptor, "sign");
        getPlayerTokenResponse.secretKey = (String) getField(message, descriptor, "secret_key");
        getPlayerTokenResponse.accountType = (int) getField(message, descriptor, "account_type");
        getPlayerTokenResponse.channelId = (int) getField(message, descriptor, "channel_id");
        getPlayerTokenResponse.extraBinData = (byte[]) getField(message, descriptor, "extra_bin_data");
        getPlayerTokenResponse.secretKeySeed = (long) getField(message, descriptor, "secret_key_seed");
        getPlayerTokenResponse.token = (String) getField(message, descriptor, "token");
        getPlayerTokenResponse.serverRandKey = (String) getField(message, descriptor, "server_rand_key");
        getPlayerTokenResponse.cloudClientIp = (int) getField(message, descriptor, "cloud_client_ip");
        getPlayerTokenResponse.birthday = (String) getField(message, descriptor, "birthday");
        getPlayerTokenResponse.tag = (int) getField(message, descriptor, "tag");
        getPlayerTokenResponse.clientIpStr = (String) getField(message, descriptor, "client_ip_str");
        getPlayerTokenResponse.subChannelId = (int) getField(message, descriptor, "sub_channel_id");
        getPlayerTokenResponse.retcode = (int) getField(message, descriptor, "retcode");
        getPlayerTokenResponse.clientVersionRandomKey = (String) getField(message, descriptor, "client_version_random_key");
        getPlayerTokenResponse.psnId = (String) getField(message, descriptor, "psn_id");
        getPlayerTokenResponse.isProficientPlayer = (boolean) getField(message, descriptor, "is_proficient_player");
        getPlayerTokenResponse.platformType = (int) getField(message, descriptor, "platform_type");
        getPlayerTokenResponse.regPlatform = (int) getField(message, descriptor, "reg_platform");
        getPlayerTokenResponse.blackUidEndTime = (int) getField(message, descriptor, "black_uid_end_time");
        getPlayerTokenResponse.unk3300Cfbhahohdkc = (boolean) getField(message, descriptor, "Unk3300_CFBHAHOHDKC");
        getPlayerTokenResponse.accountUid = (String) getField(message, descriptor, "account_uid");
        getPlayerTokenResponse.finishCollectionIdList = (List<Integer>) getField(message, descriptor, "finish_collection_id_list");
        getPlayerTokenResponse.msg = (String) getField(message, descriptor, "msg");
        getPlayerTokenResponse.countryCode = (String) getField(message, descriptor, "country_code");
        getPlayerTokenResponse.uid = (int) getField(message, descriptor, "uid");
        getPlayerTokenResponse.stopServer = new StopServerInfo().Deserialize(gameVersion, (GeneratedMessageV3) getField(message, descriptor, "stop_server"));

        return getPlayerTokenResponse;
    }

    // Setters
    public GetPlayerTokenResponse setSecurityCmdBuffer(ByteString securityCmdBuffer) {
        this.securityCmdBuffer = securityCmdBuffer;
        return this;
    }

    public GetPlayerTokenResponse setGmUid(int gmUid) {
        this.gmUid = gmUid;
        return this;
    }

    public GetPlayerTokenResponse setIsGuest(boolean isGuest) {
        this.isGuest = isGuest;
        return this;
    }

    public GetPlayerTokenResponse setUnk3300Ajbbipfmbel(boolean unk3300Ajbbipfmbel) {
        this.unk3300Ajbbipfmbel = unk3300Ajbbipfmbel;
        return this;
    }

    public GetPlayerTokenResponse setKeyId(int keyId) {
        this.keyId = keyId;
        return this;
    }

    public GetPlayerTokenResponse setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public GetPlayerTokenResponse setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public GetPlayerTokenResponse setAccountType(int accountType) {
        this.accountType = accountType;
        return this;
    }

    public GetPlayerTokenResponse setChannelId(int channelId) {
        this.channelId = channelId;
        return this;
    }

    public GetPlayerTokenResponse setExtraBinData(byte[] extraBinData) {
        this.extraBinData = extraBinData;
        return this;
    }

    public GetPlayerTokenResponse setSecretKeySeed(long secretKeySeed) {
        this.secretKeySeed = secretKeySeed;
        return this;
    }

    public GetPlayerTokenResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public GetPlayerTokenResponse setServerRandKey(String serverRandKey) {
        this.serverRandKey = serverRandKey;
        return this;
    }

    public GetPlayerTokenResponse setCloudClientIp(int cloudClientIp) {
        this.cloudClientIp = cloudClientIp;
        return this;
    }

    public GetPlayerTokenResponse setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public GetPlayerTokenResponse setTag(int tag) {
        this.tag = tag;
        return this;
    }

    public GetPlayerTokenResponse setClientIpStr(String clientIpStr) {
        this.clientIpStr = clientIpStr;
        return this;
    }

    public GetPlayerTokenResponse setSubChannelId(int subChannelId) {
        this.subChannelId = subChannelId;
        return this;
    }

    public GetPlayerTokenResponse setRetcode(int retcode) {
        this.retcode = retcode;
        return this;
    }

    public GetPlayerTokenResponse setClientVersionRandomKey(String clientVersionRandomKey) {
        this.clientVersionRandomKey = clientVersionRandomKey;
        return this;
    }

    public GetPlayerTokenResponse setPsnId(String psnId) {
        this.psnId = psnId;
        return this;
    }

    public GetPlayerTokenResponse setIsProficientPlayer(boolean isProficientPlayer) {
        this.isProficientPlayer = isProficientPlayer;
        return this;
    }

    public GetPlayerTokenResponse setPlatformType(int platformType) {
        this.platformType = platformType;
        return this;
    }

    public GetPlayerTokenResponse setRegPlatform(int regPlatform) {
        this.regPlatform = regPlatform;
        return this;
    }

    public GetPlayerTokenResponse setStopServer(StopServerInfo stopServer) {
        this.stopServer = stopServer;
        return this;
    }

    public GetPlayerTokenResponse setBlackUidEndTime(int blackUidEndTime) {
        this.blackUidEndTime = blackUidEndTime;
        return this;
    }

    public GetPlayerTokenResponse setUnk3300Cfbhahohdkc(boolean unk3300Cfbhahohdkc) {
        this.unk3300Cfbhahohdkc = unk3300Cfbhahohdkc;
        return this;
    }

    public GetPlayerTokenResponse setAccountUid(String accountUid) {
        this.accountUid = accountUid;
        return this;
    }

    public GetPlayerTokenResponse setFinishCollectionIdList(List<Integer> finishCollectionIdList) {
        this.finishCollectionIdList = finishCollectionIdList;
        return this;
    }

    public GetPlayerTokenResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public GetPlayerTokenResponse setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public GetPlayerTokenResponse setUid(int uid) {
        this.uid = uid;
        return this;
    }

    // Getters
    public ByteString getSecurityCmdBuffer() {
        return securityCmdBuffer;
    }

    public int getGmUid() {
        return gmUid;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public boolean isUnk3300Ajbbipfmbel() {
        return unk3300Ajbbipfmbel;
    }

    public int getKeyId() {
        return keyId;
    }

    public String getSign() {
        return sign;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public int getAccountType() {
        return accountType;
    }

    public int getChannelId() {
        return channelId;
    }

    public byte[] getExtraBinData() {
        return extraBinData;
    }

    public long getSecretKeySeed() {
        return secretKeySeed;
    }

    public String getToken() {
        return token;
    }

    public String getServerRandKey() {
        return serverRandKey;
    }

    public int getCloudClientIp() {
        return cloudClientIp;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getTag() {
        return tag;
    }

    public String getClientIpStr() {
        return clientIpStr;
    }

    public int getSubChannelId() {
        return subChannelId;
    }

    public int getRetcode() {
        return retcode;
    }

    public String getClientVersionRandomKey() {
        return clientVersionRandomKey;
    }

    public String getPsnId() {
        return psnId;
    }

    public boolean isProficientPlayer() {
        return isProficientPlayer;
    }

    public int getPlatformType() {
        return platformType;
    }

    public int getRegPlatform() {
        return regPlatform;
    }

    public StopServerInfo getStopServer() {
        return stopServer;
    }

    public int getBlackUidEndTime() {
        return blackUidEndTime;
    }

    public boolean isUnk3300Cfbhahohdkc() {
        return unk3300Cfbhahohdkc;
    }

    public String getAccountUid() {
        return accountUid;
    }

    public List<Integer> getFinishCollectionIdList() {
        return finishCollectionIdList;
    }

    public String getMsg() {
        return msg;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getUid() {
        return uid;
    }
}
