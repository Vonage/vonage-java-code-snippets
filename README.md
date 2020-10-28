# Vonage Quickstart Examples for Java

<img src="https://developer.nexmo.com/assets/images/Vonage_Nexmo.svg" height="48px" alt="Nexmo is now known as Vonage" />

Quickstarts also available for: [Python](https://github.com/Vonage/vonage-python-code-snippets), [.NET](https://github.com/Vonage/vonage-dotnet-code-snippets), [Node.js](https://github.com/Vonage/vonage-node-code-snippets), [PHP](https://github.com/Vonage/vonage-php-code-snippets),  [Ruby](https://github.com/Vonage/vonage-ruby-code-snippets) and [curl](https://github.com/Vonage/vonage-curl-code-snippets).

The purpose of the quickstart guide is to provide simple examples focused on one goal. For example, sending an SMS, handling an incoming SMS webhook, making a Text to Speech call. These code samples are meant to be used for https://developer.nexmo.com/, and are structured in such a way as to be used for internal testing. Developers are free to use these code snippets as a reference, but these may require changes to be worked into your specific application. We recommend checking out the Vonage Developer Website, which displays these code snippets in a more copy/paste fashion.

## Setup

To use this sample you will first need a [Vonage account][sign-up].

For some of the examples you will need to [buy a number][buy-number].

## Building The Library

You will need to have [Gradle](https://gradle.org/) installed to build the code. Once
you have gradle installed, run the following to build a jar that contains
the quickstart code along with all the vonage server sdk dependencies:

```sh
gradle assemble
```

This will build the following file: `build/libs/vonage-java-code-snippets-with-dependencies.jar`

## Running The Examples

Copy `.env-example` to `.env` and edit the values. You'll need to load those
values into environment variables, so you'll probably want to use a tool like
[Foreman](https://github.com/ddollar/foreman) to run your code like this:

```sh
foreman run java -cp build/libs/vonage-java-code-snippets-with-dependencies.jar PACKAGE.CLASS
```

So to run the OutboundTextToSpeechExample class, you would run the following:

```sh
foreman run java -cp build/libs/vonage-java-code-snippets-with-dependencies.jar com.vonage.quickstart.voice.OutboundTextToSpeech
```

If you set the environment variable `QUICKSTART_DEBUG` to any value, extra information
will be output to the console from the Vonage Server SDK.

## Running NCCO Webhook Examples

Sign up for a free [ngrok](https://ngrok.com/) account

Download and install from the ngrok site or use Homebrew (mac0S)

```sh
brew install cask ngrok
```

### Connect the installed ngrok to your ngrok account
1. Go to your ngrok dashboard.
2. Go to Setup & Installation
3. Copy the token from the **Connect your account** step without the `./` prefix. What you copy should look like this:
```shell script
ngrok authentication 112skjl4jlwlkjdl4lkj66565lkjmn56n==e4w4l
```
4. Start a HTTP tunnel forwarding to your local port. Check your snippet to locate the port ngrok should forward to.
For the Voice NCCO snippets we use port 3000, so our command would be:
```shell script
ngrok http 3000
```

You may then enter `http://localhost:4040/inspect/http` in your web browser to see a more detailed view of your requests, or use the 
console to http status and message of your requests. 

### Setup a Vonage Application 

After setting up `ngrok` you will need to setup a Vonage application that will be used for monitoring your webhooks. Add a vonage feature
that you would like your webhook to monitor for. In this example, we will setup a Vonage application and add voice capabilities. 

Setup a Vonage Application with voice capabilities using the [Vonage Developer Portal](https://dashboard.nexmo.com/)
1. On the developer portal, go to Applications.
2. Click on the **Create new application** button.
3. Give your applications a name.
4. Under *Capabilities*, toggle the *Voice* capability.
Go back to the terminal that has ngrok fired up and grab the forwarding url. Add that domain as the prefix to the path for the 
webhook. Resulting url should look similar to the following:
```sh
 http://17e80b46d273.ngrok.io/webhook/answer
```
Go back to the *Voice* capabilities section and add the urls for the webhooks. Ex:

**Answer URL:** http://17e80b46d273.ngrok.io/webhook/answer

**Event URL:** http://17e80b46d273.ngrok.io/webhook/event

**Answer Fallback URL:** http://17e80b46d273.ngrok.io/webhook/fallback (if no path is specified in the snippet use a random domain)

5. Click the **generate public and private key** button. A private key file called private.key should be downloaded to your computer.
6. Move the private key to the nexmo-java-code-snippets project root.
7. Go the developer portal and click **Generate application**
8. In your `.env` file, add the environment variables for your application that is needed to run the snippet to. For the voice dtmf webhook snippet,
we would need the *application id*, and *private key file location* 
9. (Optional) Link the number associated with your nexmo account to your app. In the developer portal, click the Link button
on the application details screen to link that number to your application. This will allow you to test webhooks that require you to 
call or text a number to test the NCCOs for that snippet.



## Request an Example

Please [raise an issue](https://github.com/nexmo-community/nexmo-java-quickstart/issues) to request an example that isn't present within the quickstart. Pull requests will be gratefully received.

## License

This code is licensed under the [MIT](LICENSE.txt) license.

[gradle]: https://gradle.org/
[foreman]: https://github.com/ddollar/foreman
[sign-up]: https://dashboard.nexmo.com/sign-up
[buy-number]: https://dashboard.nexmo.com/buy-numbers
