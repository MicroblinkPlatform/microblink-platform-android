# Release notes

## v1.10.0
### Updates

We've updated our **BlinkID Verify** and **BlinkCard** dependencies:

- The Scan & Verify ID capability now uses BlinkID Verify v3.21.0.
- The Scan Payment Card capability now uses BlinkCard v3000.0.1.

To read more about the new features supported by these upgrades, see the release notes for [BlinkID Verify](https://docs.microblink.com/verify/release-notes#version-3210) and [BlinkCard](https://docs.microblink.com/blinkcard/release-notes#v300000).

## 1.9.0
### New features
- Added support for the Upload Non-ID and Upload & Verify ID capabilities

## v1.8.0
### New features
- Added support for overriding `scanUnsupportedBack` and `scanPassportDataPageOnly` on the Scan ID step

## v1.7.0

### New features

* **Fallback consent UI**

  If `isProcessingStoringAllowed` in `MicroblinkPlatformConsent` is set to `false`, the SDK will display a built-in consent screen to the user.

  * Users can accept or reject consent directly in the SDK.
  * If consent is rejected, the session is immediately terminated.

* **Configurable proxy endpoints**

  You can now override individual proxy endpoint paths via `MicroblinkPlatformProxySettings` instead of relying on fixed URLs.
  This allows greater flexibility when integrating with custom proxy implementations.


### Breaking changes

* **`MicroblinkPlatformServiceSettings` structure updated**

  The `url` parameter has been removed and replaced with `proxySettings`.

  * Previously, you provided the full API transaction endpoint directly via `url`, for example:
    `https://www.myproxy.com/api/v1/transaction`
  * Now, you must provide proxy configuration via `MicroblinkPlatformProxySettings`, where:
    * `url` represents your proxy base domain + API suffix, **without** the final endpoint path, for example:
      `https://www.myproxy.com/api/v1`
    * The SDK constructs the full endpoint URLs internally based on the performed action.

* **New `MicroblinkPlatformProxySettings` model introduced**

  Proxy-related configuration is now encapsulated in a dedicated class:

  * `startTransactionPath` (default: `/transaction`)
  * `cancelWorkflowPath` (default: `/initialize/{workflowId}/cancel`)
  * `workflowInfoPath` (default: `/initialize/{workflowId}/info`)
  * `additionalRequestHeaders`

  The `{workflowId}` placeholder is required for cancel and workflow info paths and is validated at runtime.

* **`MicroblinkPlatformConsent` now requires `givenOn` when consent is granted**

  * The new `givenOn` field represents the timestamp (milliseconds since Unix epoch, UTC) when consent was given.
  * `givenOn` **must** be provided when `isProcessingStoringAllowed` is `true`.
  * If `isProcessingStoringAllowed` is `false`, `givenOn` may be omitted.
  
* **Verification cancellation callback updated**
 
  The result listener callback has been expanded to provide more context about the cancellation.

  * Before:
    `onVerificationCanceled()`
  * Now:
    `onVerificationCanceled(cancelState: MicroblinkPlatformCancelState)`

  The new `MicroblinkPlatformCancelState` includes:

  * optional `transactionId` of the canceled transaction
  * `cancelReason`, which can be:
    * `UserCanceled` – the user closed or navigated away from the verification flow
    * `ConsentDenied` – the user rejected the consent

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
