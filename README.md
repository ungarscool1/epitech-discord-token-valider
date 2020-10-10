# Epitech - Discord bot Token validator

## Introduction

A discord bot who validate your Epitech's activity token.

## Setup

To setup the discord bot, you need to create a config.json.

The json file need to be like that: 
```json
{
    "epiToken": "auth-<epitech token>",
    "botToken": "Your discord bot token",
    "TesseractData": "Tesseract data path"
}
```

### Get autolink
To get your epitech autolog link, go to [Epitech intra](https://intra.epitech.eu), click on "Administration" and click on "Generate autologin link".

Copy the alphanumeric string after auth and put it in epiToken field.

### Configure Tesseract

Download this [Tesseract Data set](https://github.com/tesseract-ocr/tessdata/tree/04b3a062bafba11cb61afdb760e4ca07388a49b2), extract the downloaded archive.

Put the extracted path in the TesseractData field in config.json

## Use the bot

To use the bot, you need to send a picture of your Epitech activity token.
