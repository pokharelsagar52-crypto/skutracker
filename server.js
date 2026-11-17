const { MongoClient } = require('mongodb');
const express = require('express');
const app = express();

app.use(express.json());

const uri = process.env.MONGO_URI;
const client = new MongoClient(uri);

let db;

async function startServer() {
    try {
        await client.connect();
        db = client.db("sncoldstore"); 
        console.log("✅ Connected successfully to MongoDB Atlas");

        const PORT = process.env.PORT || 3000;
        app.listen(PORT, () => {
            console.log(`🚀 Server running on port ${PORT}`);
        });
    } catch (error) {
        console.error("❌ MongoDB connection failed:", error);
        process.exit(1);
    }
}

startServer();
