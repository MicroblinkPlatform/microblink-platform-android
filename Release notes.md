# Release notes

## v1.5.0
### What's new
- We've added kiosk mode support. This is helpful in applications where a tablet device is mounted on a wall, and your users are expected to scan their documents using the front camera only. 
- You can now upload PDF files when using the `Manual Input` capability.
- The SDK is now available on [Maven Central](https://central.sonatype.com/artifact/com.microblink/microblink-platform).

### Updates
We've updated our BlinkID Verify and BlinkCard dependencies:

- The `Scan & Verify ID` capability now uses `BlinkID Verify 3.14.1`.
- The `Scan Payment Card` capability now uses `BlinkCard 2.12.0`.

To read more about the new features supported by these upgrades, see the release notes for [BlinkID Verify](https://blinkidverify.docs.microblink.com/docs/docver/release-notes/release-notes) and [BlinkCard](https://blinkcard.docs.microblink.com/category/release-notes/).

## 1.4.3
### Bugfixes
- Fixed an issue where the SDK would not update after returning from the document scanning on some older Samsung devices.


## 1.4.2
### Breaking changes

- `MicroblinkPlatformServiceSettings` parameter update

	- The field `hostUrl` has been renamed to `url`.

	- Previously, `hostUrl` accepted only the base domain (e.g. `https://www.myapi.com`).

	- Now, url must include the full API transaction endpoint, for example: `https://www.myapi.com/api/v1/transaction`


### Migration Guide

- Before:
```
MicroblinkPlatformServiceSettings(
    workflowId = "myWorkflow",
    hostUrl = "https://www.myapi.com",
    consent = consent
)
```

- Now:
```
MicroblinkPlatformServiceSettings(
    workflowId = "myWorkflow",
    url = "https://www.myapi.com/api/v1/transaction",
    consent = consent
)
```

⚠️ This is a breaking change. All integrations must update to use the new url parameter with the full endpoint.

## v1.4.1
### Improvements
- Improved extraction of first and middle names for certain ID types

## 1.4.0
### Improvements
- Face capture now performs additional validation checks:
	- Eyes closed
	- Image too dark
	- Face blurry
	- Mouth open
	- Sunglasses detected

- Improved card scanning performance and reliability
- Enhanced document extraction with additional extracted fields

## 1.3.0
### New features
- Added support for payment card scan capability:

	- `mbpCardScanResultListener` added to `MicroblinkPlatformConfig` for handling card scan results.

	- New interface `MicroblinkPlatformCardScanResultListener` with `onCardScanned(cardResult: CardScanResult)` callback.

	- `CardScanResult` data class containing card number, expiry date, owner, and CVV.

	- `Date` data class to represent expiry date with optional day, month, and year fields.


- Added support for multiple steps of the same type

## 1.2.0
- Added support for User input capability

## 1.1.0
- Added support for BlinkID

## 1.0.0
- Microblink Platform Android SDK initial release
