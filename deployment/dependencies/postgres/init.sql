-- Create test databases for master also create specific user for it
create user asset superuser;
alter user asset password 'asset2022';

-- Create master database
create database assets;
grant all privileges on database assets to asset;