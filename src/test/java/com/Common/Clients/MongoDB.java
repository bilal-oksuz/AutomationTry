package com.Common.Clients;

import com.Common.Base.Config;
import com.Common.Base.JavaInfastructure;
import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.bson.UuidRepresentation;
import org.bson.codecs.UuidCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import javax.print.Doc;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class MongoDB
{
    private MongoCollection mongoCollection;
    private String dbName;
    public MongoClient mongoClient;
    public com.mongodb.client.MongoClient client;

    public MongoDB()
    {


    }

    public MongoCollection ConnectMongoWithJavaDriver(String url, String databaseName, String CollectionName)
    {
        client = MongoClients.create(buildMongoClientSettingsForJava(url));
        MongoCollection collection = (client.getDatabase(databaseName)).getCollection(CollectionName);
        return collection;
    }

    public MongoCollection ConnectMongoWithCSharp(String url, String databaseName, String CollectionName)
    {
        client = MongoClients.create(buildMongoClientSettingsForCSharp(url));
        MongoCollection collection = (client.getDatabase(databaseName)).getCollection(CollectionName);
        return collection;
    }

    public MongoCollection ConnectMongoWithStandart(String url, String databaseName, String CollectionName)
    {
        client = MongoClients.create(buildMongoClientSettingsForStandart(url));
        MongoCollection collection = (client.getDatabase(databaseName)).getCollection(CollectionName);
        return collection;
    }

    public MongoCollection ConnectMongoWithJavaDriverForCommand(String url, String databaseName, String CollectionName)
    {
        client = MongoClients.create(buildMongoClientSettingsForJava(url,"Write"));
        MongoCollection collection = (client.getDatabase(databaseName)).getCollection(CollectionName);
        return collection;
    }

    public MongoCollection ConnectMongoWithCSharpForCommand(String url, String databaseName, String CollectionName)
    {
        client = MongoClients.create(buildMongoClientSettingsForCSharp(url,"Write"));
        MongoCollection collection = (client.getDatabase(databaseName)).getCollection(CollectionName);
        return collection;
    }

    public MongoCollection ConnectMongoWithStandartForCommand(String url, String databaseName, String CollectionName)
    {
        client = MongoClients.create(buildMongoClientSettingsForStandart(url,"Write"));
        MongoCollection collection = (client.getDatabase(databaseName)).getCollection(CollectionName);
        return collection;
    }


    private MongoClientSettings buildMongoClientSettingsForJava(String clusterUrl)
    {
        return MongoClientSettings.builder().readPreference(ReadPreference.secondaryPreferred())
                    .applyConnectionString(new ConnectionString(clusterUrl)).codecRegistry(codecRegistriesForJava())
                    .build();
    }

    private MongoClientSettings buildMongoClientSettingsForCSharp(String clusterUrl) {
        return MongoClientSettings.builder().readPreference(ReadPreference.secondaryPreferred())
                .applyConnectionString(new ConnectionString(clusterUrl)).codecRegistry(codecRegistriesForCSharp())
                .build();
    }

    private MongoClientSettings buildMongoClientSettingsForStandart(String clusterUrl) {
        return MongoClientSettings.builder().readPreference(ReadPreference.secondaryPreferred())
                .applyConnectionString(new ConnectionString(clusterUrl)).codecRegistry(codecRegistriesForStandart())
                .build();
    }

    private MongoClientSettings buildMongoClientSettingsForJava(String clusterUrl,String process)
    {
        if(process.equals("Write"))
        {
            return MongoClientSettings.builder().readPreference(ReadPreference.primaryPreferred())
                    .applyConnectionString(new ConnectionString(clusterUrl)).codecRegistry(codecRegistriesForJava())
                    .build();
        }
        else
        {
            return MongoClientSettings.builder().readPreference(ReadPreference.secondaryPreferred())
                    .applyConnectionString(new ConnectionString(clusterUrl)).codecRegistry(codecRegistriesForJava())
                    .build();
        }

    }

    private MongoClientSettings buildMongoClientSettingsForCSharp(String clusterUrl,String process)
    {
        if(process.equals("Write"))
        {
        return MongoClientSettings.builder().readPreference(ReadPreference.primaryPreferred())
                .applyConnectionString(new ConnectionString(clusterUrl)).codecRegistry(codecRegistriesForCSharp())
                .build();
        }
        else
        {
            return MongoClientSettings.builder().readPreference(ReadPreference.secondaryPreferred())
                    .applyConnectionString(new ConnectionString(clusterUrl)).codecRegistry(codecRegistriesForCSharp())
                    .build();
        }
    }

    private MongoClientSettings buildMongoClientSettingsForStandart(String clusterUrl,String process)
    {
        if(process.equals("Write")) {
            return MongoClientSettings.builder().readPreference(ReadPreference.primaryPreferred())
                    .applyConnectionString(new ConnectionString(clusterUrl)).codecRegistry(codecRegistriesForStandart())
                    .build();
        }
        else
        {
            return MongoClientSettings.builder().readPreference(ReadPreference.secondaryPreferred())
                    .applyConnectionString(new ConnectionString(clusterUrl)).codecRegistry(codecRegistriesForStandart())
                    .build();
        }
    }



    private CodecRegistry codecRegistriesForJava() {
        return CodecRegistries.fromRegistries(
                CodecRegistries.fromProviders(new UuidCodecProvider(UuidRepresentation.JAVA_LEGACY)),
                MongoClientSettings.getDefaultCodecRegistry()
        );
    }

    private CodecRegistry codecRegistriesForCSharp() {
        return CodecRegistries.fromRegistries(
                CodecRegistries.fromProviders(new UuidCodecProvider(UuidRepresentation.C_SHARP_LEGACY)),
                MongoClientSettings.getDefaultCodecRegistry()
        );
    }

    private CodecRegistry codecRegistriesForStandart() {
        return CodecRegistries.fromRegistries(
                CodecRegistries.fromProviders(new UuidCodecProvider(UuidRepresentation.STANDARD)),
                MongoClientSettings.getDefaultCodecRegistry()
        );
    }


    public MongoClient OpenMongoClient(String url)
    {
        MongoClientURI connectionString = new MongoClientURI(url);
        this.mongoClient = new MongoClient(connectionString);
        this.mongoClient.setReadPreference(ReadPreference.secondaryPreferred());
        return  this.mongoClient;
    }



    public void CloseMongoClient()
    {
        if(!JavaInfastructure.isNull(mongoClient))
        {
            mongoClient.close();
        }

        if(!JavaInfastructure.isNull(client))
        {
            client.close();
        }
    }


    public void CloseMongoDBClients()
    {
        client.close();
    }



    public MongoDatabase ConnectToMongoDatabase(String url,String databaseName)
    {
        mongoClient=OpenMongoClient(url);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        return mongoDatabase;
    }

    public MongoCollection ConnectToMongoCollection(String url,String databaseName,String CollectionName)
    {
        mongoClient=OpenMongoClient(url);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        MongoCollection collection =  mongoDatabase.getCollection(CollectionName);
        return collection;
    }

    public MongoCollection ConnectToMongoCollection(MongoDatabase database,String CollectionName)
    {
        MongoDatabase mongoDatabase = database;
        MongoCollection collection =  mongoDatabase.getCollection(CollectionName);
        return collection;
    }

    public Document sendQueryToDbFirstResult(MongoCollection<Document> mongoCollection)
    {
        Document myDoc = mongoCollection.find().first();
        CloseMongoClient();
        return  myDoc;
    }

    public ArrayList<Document> sendQueryToDbResult(MongoCollection mongoCollection)
    {
        MongoCursor<Document> cursor = mongoCollection.find().iterator();
        ArrayList<Document> documentList = new ArrayList<>();
        while (cursor.hasNext())
        {
            Document document;
            document=cursor.next();
            documentList.add(document);
        }
        return documentList;
    }

    public ArrayList<Document> sendQueryToDbResultWithQueryBuilder(MongoCollection mongoCollection,QueryBuilder queryBuilder)
    {
        MongoCursor<Document> cursor = mongoCollection.find((Bson) queryBuilder.get()).iterator();
        ArrayList<Document> documentList = new ArrayList<>();
        while (cursor.hasNext())
        {
            Document document;
            document=cursor.next();
            documentList.add(document);
        }
        return documentList;
    }



    public Document sendQueryToDbFirstResultByDocument(MongoCollection<Document> mongoCollection, QueryBuilder builder)
    {
        Document myDoc=  mongoCollection.find((Bson)builder.get()).first();
        CloseMongoClient();
        return  myDoc;
    }

    public JSONObject sendQueryToDbFirstResultJson(MongoCollection<Document> mongoCollection, QueryBuilder queryBuilder)
    {
        Document document=new Document();
        JSONObject jsonObject=null;
        MongoCursor<Document> cursor = mongoCollection.find((Bson)queryBuilder.get()).iterator();
        try
        {
            while (cursor.hasNext())
            {
                document.putAll(cursor.next());
                jsonObject=new JSONObject(document.toJson());
                break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            cursor.close();
            CloseMongoClient();
        }
        return jsonObject;
    }

    public Document sendQueryToDbFirstResult(MongoCollection<Document> mongoCollection, QueryBuilder queryBuilder)
    {
        Document document=new Document();
        MongoCursor<Document> cursor = mongoCollection.find((Bson)queryBuilder.get()).iterator();
        try
        {
            while (cursor.hasNext())
            {
                document.putAll(cursor.next());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            cursor.close();
            CloseMongoClient();
        }
        return document;
    }

    public Document sendQueryWithQueryBuilderByDocument(MongoCollection<Document> mongoCollection, QueryBuilder queryBuilder)
    {
        Document document=new Document();
        MongoCursor<Document> cursor = mongoCollection.find((Bson)queryBuilder.get()).iterator();
        try
        {
            while (cursor.hasNext())
            {
                document.putAll(cursor.next());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            cursor.close();
            CloseMongoClient();
        }
        return document;
    }

    public JSONObject sendQuerywithQueryBuilder(MongoCollection mongoCollection, QueryBuilder queryBuilder)
    {
        Document document=new Document();
        JSONObject jsonObject=null;
        MongoCursor<Document> cursor = mongoCollection.find((Bson)queryBuilder.get()).iterator();
        try
        {
            if (cursor.hasNext())
            {
                document.putAll(cursor.next());
                jsonObject=new JSONObject(document);
            }
            else
            {
                jsonObject=null;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            cursor.close();
            CloseMongoClient();
        }
        return jsonObject;
    }

    public ArrayList<Document> sendQuerywithQueryBuilderForDocuments(MongoCollection mongoCollection, QueryBuilder queryBuilder)
    {
        ArrayList<Document> list=new ArrayList<>();
        MongoCursor<Document> cursor = mongoCollection.find((Bson)queryBuilder.get()).iterator();
        try
        {
            while (cursor.hasNext())
            {
                Document document = new Document();
                document.putAll(cursor.next());
                list.add(document);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            cursor.close();
            CloseMongoClient();
        }
        return list;
    }

    public JSONObject sendQuerywithBasicObj(MongoCollection mongoCollection, ArrayList<BasicDBObject> query)
    {
        Object document=new Object();
        JSONObject jsonObject=null;
        BasicDBObject andQuery = new BasicDBObject();
        andQuery.put("$and", query);

        MongoCursor cursor=mongoCollection.find(andQuery).iterator();
        try
        {
            while (cursor.hasNext())
            {
                document=cursor.next();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            cursor.close();
            CloseMongoClient();
        }
        jsonObject=new JSONObject(document);
        return jsonObject;
    }

    public JSONObject sendQuerywithBasicObj(MongoCollection mongoCollection, BasicDBObject query)
    {
        Document document=new Document();
        MongoCursor<Document> cursor = mongoCollection.find((Bson)query).iterator();
        try
        {
            while (cursor.hasNext())
            {
                document.putAll(cursor.next());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            cursor.close();
            CloseMongoClient();
        }
        return new JSONObject(document.toJson());
    }

    public Document sendQuerywithBasicObject(MongoCollection mongoCollection, BasicDBObject query)
    {
        Document document=new Document();
        MongoCursor<Document> cursor = mongoCollection.find((Bson)query).iterator();
        try
        {
            while (cursor.hasNext())
            {
                document.putAll(cursor.next());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            cursor.close();
            CloseMongoClient();
        }
        return document;
    }

    public boolean deleteDocumentByQuery(MongoCollection mongoCollection, QueryBuilder queryBuilder)
    {
        ArrayList<Document> list=new ArrayList<>();
        MongoCursor<Document> cursor = mongoCollection.find((Bson)queryBuilder.get()).iterator();
        try
        {
            while (cursor.hasNext())
            {
                Document document = new Document();
                document.putAll(cursor.next());
                list.add(document);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(list.isEmpty())
            {
                System.out.println("Silinecek kayıt bulunamadı.");
                return false;
            }

            else
            {
                DeleteResult deleteResult=mongoCollection.deleteMany((Bson)queryBuilder.get());
                if (deleteResult.getDeletedCount()>0)
                {
                    cursor = mongoCollection.find((Bson)queryBuilder.get()).iterator();
                    if(cursor.hasNext())
                    {
                        return false;
                    }
                    return true;
                }
                else
                {
                    return false;
                }

            }
        }
    }

    public boolean deleteDocumentByObjectId(MongoCollection mongoCollection, ObjectId _id)
    {
        ArrayList<Document> list=new ArrayList<>();
        QueryBuilder queryBuilder=QueryBuilder.start("_id").is(_id);
        MongoCursor<Document> cursor = mongoCollection.find((Bson)queryBuilder.get()).iterator();
        try
        {
            while (cursor.hasNext())
            {
                Document document = new Document();
                document.putAll(cursor.next());
                list.add(document);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(list.isEmpty())
            {
                return false;
            }

            else
            {
                DeleteResult deleteResult=mongoCollection.deleteOne((Bson)queryBuilder.get());
                if (deleteResult.wasAcknowledged())
                {
                    cursor = mongoCollection.find((Bson)queryBuilder.get()).iterator();
                    if (cursor.hasNext())
                    {
                        return false;
                    }
                    return true;
                }
                else
                {
                    return false;
                }

            }
        }
    }

    public Document addGroup(String key)
    {
        String group="$"+key;
        return new Document("$group", new Document("_id", group));
    }

    public Document addMatch(String key,String value)
    {
        return new Document("$match", new Document(key, value));
    }

    public Document addMatch(String key,boolean value)
    {
        return new Document("$match", new Document(key, value));
    }

    public Document addMatch(String key,int value)
    {
        return new Document("$match", new Document(key, value));
    }

    public Document addMatch(HashMap<String,Object> objectMap)
    {
        Document document=new Document();
        for (String key:objectMap.keySet())
        {
            document.append(key,objectMap.get(key));
        }

        return new Document("$match",document);
    }

    public Document addUnwind(String key)
    {
        String unwind="$"+key;
        return new Document("$unwind", unwind);
    }

    public Document addProject(String key)
    {
        return new Document("$project", new Document(key,1));
    }

    public Document addProject(ArrayList<String> key)
    {
        Document document=new Document();
        for (int i = 0; i <key.size() ; i++)
        {
            document.append(key.get(i),1);
        }
        return new Document("$project", document);
    }

    public Document addSortDesc(String key)
    {
        return new Document("$sort", new Document(key,-1));
    }

    public Document addSortAsc(String key)
    {
        return new Document("$sort", new Document(key,1));
    }


    public void setMongoCollection(MongoCollection mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public String getDbName() {
        return dbName;
    }

    public void insertOneToCollectionFromJson(MongoCollection mongoCollection,String json)
    {
       Document document=new Document().parse(json);
       document.append("CreatedDate",new Date());
       document.append("UpdatedDate",new Date());
       mongoCollection.insertOne(document);
    }

    public void insertOneToCollectionFromObject(MongoCollection mongoCollection,Object object)
    {
        DBObject obj = (DBObject) JSON.parse(new JSONObject(object).toString());
        mongoCollection.insertOne(obj);
        System.out.println("Done");
    }

    public void updateOneDataToCollectionWithWhereCondition(MongoCollection mongoCollection,Map<String,String> field,String newValue)
    {
        mongoCollection.updateOne(eq(field.get("key"), field.get("value")), new Document("$set", new Document(field.get("key"), newValue)));
        CloseMongoClient();
    }

    public void updateOneDataToCollectionWithWhereCondition(MongoCollection mongoCollection,BasicDBObject basicDBObject,String key,Object newValue)
    {
        mongoCollection.updateOne(basicDBObject, new Document("$set", new Document(key, newValue)));
        CloseMongoClient();
    }

    public void updateDataToAllCollection(MongoCollection mongoCollection,Map<String,String> field,String newValue)
    {
        mongoCollection.updateMany(null, new Document("$set", new Document(field.get("key"), newValue)));
        CloseMongoClient();
    }

    public void insertToDataListToCollection(MongoCollection mongoCollection, Map<String,String> Object)
    {
        Document document1 = new Document();
        document1.put("CountryCode", "TUR");
        document1.put("CityCode", "34");
        document1.put("TownCode", "1663");
        document1.put("DistrictCode", "1390");


        MongoCollection<Document> collection=mongoCollection;
        collection.insertOne(document1);
        CloseMongoClient();

        /*MongoCollection<Document> collection=mongoCollection;
        List<Document> documents = new ArrayList<Document>();
        for (int i = 0; i < 100; i++)
        {
            documents.add(new Document("i", i));
        }
        collection.insertMany(documents);*/
    }

    public static void main(String args[]) {
    }


}
