# Release notes

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