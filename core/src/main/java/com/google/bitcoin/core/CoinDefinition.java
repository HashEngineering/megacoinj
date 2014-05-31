package com.google.bitcoin.core;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: HashEngineering
 * Date: 8/13/13
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {


    public static final String coinName = "Megacoin";
    public static final String coinTicker = "MEC";
    public static final String coinURIScheme = "megacoin";
    public static final String cryptsyMarketId = "45";
    public static final String cryptsyMarketCurrency = "BTC";
    public static final String PATTERN_PRIVATE_KEY_START = "6";

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;


    public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://mec.blockr.io/";    //blockr.io
    public static final String BLOCKEXPLORER_ADDRESS_PATH = "address/info/";             //blockr.io path
    public static final String BLOCKEXPLORER_TRANSACTION_PATH = "tx/info/";              //blockr.io path
    public static final String BLOCKEXPLORER_BLOCK_PATH = "block/info/";                 //blockr.io path
    public static final String BLOCKEXPLORER_BASE_URL_TEST = BLOCKEXPLORER_BASE_URL_PROD;


    public static final String DONATION_ADDRESS = "MVB27rjvdL36pszWVeBgRMUbc2hQB2Scsz";  //HashEngineering donation DGC address

    enum CoinHash {
        SHA256,
        scrypt,
    };
    public static final CoinHash coinPOWHash = CoinHash.scrypt;

    public static boolean checkpointFileSupport = true;
    //Original Values
    public static final int TARGET_TIMESPAN_0 = (int)(6 * 60 * 3 * 20);  // 3.5 days per difficulty cycle, on average.
    public static final int TARGET_SPACING_0 = (int)(150);  // 2.5 minutes per block.
    public static final int INTERVAL_0 = TARGET_TIMESPAN_0 / TARGET_SPACING_0;  //2016 blocks

    public static final int TARGET_TIMESPAN = (int)(150 * 9);  // 22.5 minutes per difficulty cycle, on average.
    public static final int TARGET_SPACING = (int)(150);  // 2.5 minutes per block.
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;  //9 blocks

    public static final int TARGET_TIMESPAN_3 = 150;  // 22.5 minutes per difficulty cycle, on average.
    public static final int TARGET_SPACING_3 = (int)(150);  // 2.5 minutes per block.
    public static final int INTERVAL_3 = TARGET_TIMESPAN_3 / TARGET_SPACING_3;  //9 blocks

    static final long nTargetSpacing = 150; // 2.5 minutes
    static final long nOriginalInterval = 2016;
    static final long nFilteredInterval =    9;
    static final long nOriginalTargetTimespan = nOriginalInterval * nTargetSpacing; // 3.5 days
    static final long nFilteredTargetTimespan = nFilteredInterval * nTargetSpacing; // 22.5 minutes

    public static int DIFF_FILTER_THRESHOLD_TESTNET = 8192;
    public static int DIFF_FILTER_THRESHOLD = 8192;

    public static int nDifficultySwitchHeight = 8192;
    public static int nDifficultySwitchHeightTwo = 62773;



    public static final int getInterval(int height, boolean testNet) {
        if(height < nDifficultySwitchHeight)
            return (int)nOriginalInterval;    //1080
        else if(height < nDifficultySwitchHeightTwo)
            return (int)nFilteredInterval;      //108
        else return INTERVAL_3;
    }
    public static final int getIntervalForCheckpoints(int height, boolean testNet) {
        if(height < 8050)
            return (int)nOriginalInterval;    //2016
        else if(height < nDifficultySwitchHeightTwo)
            return (int)nOriginalInterval;      //2016
        else return (int)nOriginalInterval / 4; //504
    }
    public static final int getIntervalCheckpoints() {
            return INTERVAL_0;    //1080

    }
    public static final int getTargetTimespan(int height, boolean testNet) {
        if(height < nDifficultySwitchHeight)
            return TARGET_TIMESPAN_0;  //3.5 days
        else
            return TARGET_TIMESPAN;    //72 min
    }
    public static int getMaxTimeSpan(int value, int height, boolean testNet)
    {
        if(height < nDifficultySwitchHeight)
            return value * 4;
        else
            return value * 1;   // not used
    }
    public static int getMinTimeSpan(int value, int height, boolean testNet)
    {
        if(height < nDifficultySwitchHeight)
            return value / 4;
        else
            return value * 1;    //not used
    }

    public static int spendableCoinbaseDepth = 100; //main.h: static const int COINBASE_MATURITY
    public static final BigInteger MAX_MONEY = BigInteger.valueOf(42000000).multiply(Utils.COIN);                 //main.h:  MAX_MONEY

    public static final BigInteger DEFAULT_MIN_TX_FEE = BigInteger.valueOf(10000);   // MIN_TX_FEE
    public static final BigInteger DUST_LIMIT = Utils.CENT; //main.h CTransaction::GetMinFee        0.01 coins

    public static final int PROTOCOL_VERSION = 70001;          //version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 209;        //version.h MIN_PROTO_VERSION


    public static final int BLOCK_CURRENTVERSION = 1;   //CBlock::CURRENT_VERSION
    public static final int MAX_BLOCK_SIZE = 1 * 1000 * 1000;


    public static final boolean supportsBloomFiltering = true; //Requires PROTOCOL_VERSION 70000 in the client

    public static final int Port    = 7951;       //protocol.h GetDefaultPort(testnet=false)
    public static final int TestPort = 17951;     //protocol.h GetDefaultPort(testnet=true)

    //
    //  Production
    //
    public static final int AddressHeader = 50;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 5;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS
    public static final boolean allowBitcoinPrivateKey = true; //for backward compatibility with previous version of digitalcoin
    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
    public static final long PacketMagic = 0xede0e4ee;      //0xfb, 0xc0, 0xb6, 0xdb

    //Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long genesisBlockTime = 1369197853L;                       //main.cpp: LoadBlockIndex
    static public long genesisBlockNonce = (2084576387);                         //main.cpp: LoadBlockIndex
    static public String genesisHash = "7520788e2d99eec7cf6cf7315577e1268e177fff94cb0a7caf6a458ceeea9ac2"; //main.cpp: hashGenesisBlock
    static public int genesisBlockValue = 500;                                                              //main.cpp: LoadBlockIndex
    //taken from the raw data of the block explorer
    static public String genesisXInBytes = "04ffff001d01044c4c426f73746f6e20486572616c64202d2032312f4d61792f32303133202d20495253204f6666696369616c20746f2054616b6520466966746820746f2041766f69642054657374696679696e67";   //"Boston Herald - 21/May/2013 - IRS Official to Take Fifth to Avoid Testifying"
    static public String genessiXOutBytes = "0411582e4e718df82c9d75a4886ab7602f0a1b866d81a4e44acba04e847ccd0514b97bee4a61fa73b1474d12851422b01565f244f722f318f1608258b74a3f3fe6";

    //net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
            "144.76.166.163",
            "192.99.9.229",
            "54.186.9.232",
            "mega.rapta.net",
            "minepool.net",
            "78.46.22.103",
            "67.238.163.221",
            "217.65.56.200",
            "67.210.249.29",
    };

    public static int minBroadcastConnections = 1;   //0 for default; we need more peers.

    //
    // TestNet - digitalcoin - not tested
    //
    public static final boolean supportsTestNet = false;
    public static final int testnetAddressHeader = 111;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 196;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0xfdf0f4fe;      //0xfc, 0xc1, 0xb7, 0xdc
    public static final String testnetGenesisHash = "5e039e1ca1dbf128973bf6cff98169e40a1b194c3b91463ab74956f413b2f9c8";
    static public long testnetGenesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 1369198853L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (386245382);                         //main.cpp: LoadBlockIndex





    //main.cpp GetBlockValue(height, fee)
    public static BigInteger GetBlockReward(int height)
    {
        int COIN = 1;
        BigInteger nSubsidy = Utils.toNanoCoins(500,0);
	/*
		Total Coins: 42 Million
		* 1st 5 Months, 21 Million Coins will be generated
		  Every 21,000 Blocks (1 Month) the reward steps down from 500, 250, 125, 75, 50.

		* Through the next few decades the Remaining 21 Million will be generated
		  Every 420,000 Blocks (2 Years), The reward starts at 25 and is Halved each period
		  10.5 Million come from first 2 Years of 420K Blocks
	*/
        int BlockCountA = 21000;
        int BlockCountB = 420000;
        if (height >= BlockCountA * 5)
        {
            nSubsidy = Utils.toNanoCoins(25 * COIN, 0);
            nSubsidy = nSubsidy.shiftRight(((height - (BlockCountA * 5)) / (BlockCountB))); // Subsidy is cut in half every 420000 blocks
        }
        else if (height >= BlockCountA * 4) { nSubsidy = Utils.toNanoCoins(50 * COIN, 0); }
        else if (height >= BlockCountA * 3) { nSubsidy = Utils.toNanoCoins(75 * COIN, 0); }
        else if (height >= BlockCountA * 2) { nSubsidy = Utils.toNanoCoins(125 * COIN, 0); }
        else if (height >= BlockCountA) { nSubsidy = Utils.toNanoCoins(250 * COIN, 0); }
        else { nSubsidy = Utils.toNanoCoins(500,0); }

        return nSubsidy;
    }

    public static int subsidyDecreaseBlockCount = 420000;     //main.cpp GetBlockValue(height, fee)

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20); // digitalcoin: starting difficulty is 1 / 2^12

    static public String[] testnetDnsSeeds = new String[] {
          "not supported"
    };
    //from main.h: CAlert::CheckSignature
    public static final String SATOSHI_KEY = "04680D5270D3E6BA9E2671D889778FC4A0753FF036171D553A179635DDE67146481AB4EA7A6856CBB6A97AE16E7820F2F318E88F21E9BCFB0380E52BD63306C05C";
    public static final String TESTNET_SATOSHI_KEY = "04826AC11FCF383A1E0F21E2A76807D082FF4E7F139111A7768E4F5A35A5653A2D44A8E19BC8B55AEDC9F9238D424BDC5EBD6D2BAF9CB3D30CEDEA35C47C8350A0";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "org.megacoin.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "org.megacoin.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.google.megacoin.unittest";


    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
        checkpoints.put(   5215, new Sha256Hash("f8a7fec79eeee3228499601c614b179d09b1b08bd58515be8a2795f6baafb493"));
        checkpoints.put(   7333, new Sha256Hash("f798f245214a5ed83c69d362a88c48047c82675d3bf84b0c88d6f1c71ed4b372"));
        checkpoints.put(   9850, new Sha256Hash("03359392dab8f47cd70626bb978c7658a385a49570bd953a1c9f8ceed4ff8be6"));
        checkpoints.put(  10000, new Sha256Hash("e17057f2114a45827acbcfe2a55b273ccabd3dc8982765f7a3fbca166811909b"));
        checkpoints.put(  15480, new Sha256Hash("f682a44431fff21c5d412f97e92f3340c59527626f6b5a4c6b85de78e009f177"));
        checkpoints.put(  17625, new Sha256Hash("5c4df33d72bef0c9e95bcce997e0c1d5985e957a16aebbf463a8bbee1ab9eb4b"));
        checkpoints.put(  21190, new Sha256Hash("e960216ff0e3ebae632af05792f0a11bf8a8f61e4f5ef43fcfd84b6e8053ec59"));
        checkpoints.put(  35500, new Sha256Hash("b88a54ce5d247a46166ff28228c62efc495cfb8d0dadfa895bced191cb261c90"));
        checkpoints.put(  39500, new Sha256Hash("3eb61d9897009caa6ed492fc154121849166d7a627f26322eae9cf32c7dc753b"));
        checkpoints.put(  44400, new Sha256Hash("eb5af032f88981810f31f13e38e33c86585dbf963ea6de199382706dc5b3aee4"));
        checkpoints.put(  59300, new Sha256Hash("558cf1f1fe31eb0816f9fc73900133564c29b50b626cbf82c69860fd3583653c"));
        checkpoints.put(  62767, new Sha256Hash("5bd5d25c8a19b764634435b9ab1121b4678fbf6e6ad771b252f75f3cdfa82131"));
        checkpoints.put(  96800, new Sha256Hash("f972b9421ac790af82cd63f5db1dbbee114ce3476486d4335f46c6d7d8897671"));
    }

    //Unit Test Information
    public static final String UNITTEST_ADDRESS = "DPHYTSm3f96dHRY3VG1vZAFC1QrEPkEQnt";
    public static final String UNITTEST_ADDRESS_PRIVATE_KEY = "QU1rjHbrdJonVUgjT7Mncw7PEyPv3fMPvaGXp9EHDs1uzdJ98hUZ";

}
