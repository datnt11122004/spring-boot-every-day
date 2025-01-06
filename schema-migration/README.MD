### THIS FOLDER IS SUPPORT FOR SCHEMA MIGRATION IN DATABASE BY PRISMA

#### Setup Prisma

1. Go to this folder
```bash
cd schema-migration
```

2. Install Prisma
```bash
npm install
```

3. Generate Migration
    ##### Step 1. Change the schema in `schema.prisma`
    Example:
    ```
    model User {
      id        Int      @id @default(autoincrement())
      email     String   @unique @map("email")
      name      String?  @map("name")
      posts     Post[]   @relation("posts")
      profile   Profile? @relation("profile")
   
        @@map("users")
    }
   ```

   ##### Step 2. Create Migration
    ```bash
     npm run prisma:migrate-create
    ```

   ##### Step 3. Copy Migration applied to flyway
