package kvalidator.i18n

val ru = mapOf(
        "accepted" to "Вы должны принять :attribute.",
        "active_url" to "Поле :attribute содержит недействительный URL.",
        "after" to "В поле :attribute должна быть дата после :date.",
        "after_or_equal" to "В поле :attribute должна быть дата после или равняться :date.",
        "alpha" to "Поле :attribute может содержать только буквы.",
        "alpha_dash" to "Поле :attribute может содержать только буквы, цифры, дефис и нижнее подчеркивание.",
        "alpha_num" to "Поле :attribute может содержать только буквы и цифры.",
        "array" to "Поле :attribute должно быть массивом.",
        "before" to "В поле :attribute должна быть дата до :date.",
        "before_or_equal" to "В поле :attribute должна быть дата до или равняться :date.",
        "between.numeric" to "Поле :attribute должно быть между :min и :max.",
        "between.file" to "Размер файла в поле :attribute должен быть между :min и :max Килобайт(а).",
        "between.string" to "Количество символов в поле :attribute должно быть между :min и :max.",
        "between.array" to "Количество элементов в поле :attribute должно быть между :min и :max.",
        "boolean" to "Поле :attribute должно иметь значение логического типа.",
        "confirmed" to "Поле :attribute не совпадает с подтверждением.",
        "date" to "Поле :attribute не является датой.",
        "date_equals" to "Поле :attribute должно быть датой равной :date.",
        "date_format" to "Поле :attribute не соответствует формату :format.",
        "different" to "Поля :attribute и :other должны различаться.",
        "digits" to "Длина цифрового поля :attribute должна быть :digits.",
        "digits_between" to "Длина цифрового поля :attribute должна быть между :min и :max.",
        "dimensions" to "Поле :attribute имеет недопустимые размеры изображения.",
        "distinct" to "Поле :attribute содержит повторяющееся значение.",
        "email" to "Поле :attribute должно быть действительным электронным адресом.",
        "ends_with" to "Поле :attribute должно заканчиваться одним из следующих значений: :values",
        "exists" to "Выбранное значение для :attribute некорректно.",
        "file" to "Поле :attribute должно быть файлом.",
        "filled" to "Поле :attribute обязательно для заполнения.",
        "gt.numeric" to "Поле :attribute должно быть больше :value.",
        "gt.file" to "Размер файла в поле :attribute должен быть больше :value Килобайт(а).",
        "gt.string" to "Количество символов в поле :attribute должно быть больше :value.",
        "gt.array" to "Количество элементов в поле :attribute должно быть больше :value.",
        "gte.numeric" to "Поле :attribute должно быть больше или равно :value.",
        "gte.file" to "Размер файла в поле :attribute должен быть больше или равен :value Килобайт(а).",
        "gte.string" to "Количество символов в поле :attribute должно быть больше или равно :value.",
        "gte.array" to "Количество элементов в поле :attribute должно быть больше или равно :value.",
        "image" to "Поле :attribute должно быть изображением.",
        "in" to "Выбранное значение для :attribute ошибочно.",
        "in_array" to "Поле :attribute не существует в :other.",
        "integer" to "Поле :attribute должно быть целым числом.",
        "ip" to "Поле :attribute должно быть действительным IP-адресом.",
        "ipv4" to "Поле :attribute должно быть действительным IPv4-адресом.",
        "ipv6" to "Поле :attribute должно быть действительным IPv6-адресом.",
        "json" to "Поле :attribute должно быть JSON строкой.",
        "lt.numeric" to "Поле :attribute должно быть меньше :value.",
        "lt.file" to "Размер файла в поле :attribute должен быть меньше :value Килобайт(а).",
        "lt.string" to "Количество символов в поле :attribute должно быть меньше :value.",
        "lt.array" to "Количество элементов в поле :attribute должно быть меньше :value.",
        "lte.numeric" to "Поле :attribute должно быть меньше или равно :value.",
        "lte.file" to "Размер файла в поле :attribute должен быть меньше или равен :value Килобайт(а).",
        "lte.string" to "Количество символов в поле :attribute должно быть меньше или равно :value.",
        "lte.array" to "Количество элементов в поле :attribute должно быть меньше или равно :value.",
        "max.numeric" to "Поле :attribute не может быть более :max.",
        "max.file" to "Размер файла в поле :attribute не может быть более :max Килобайт(а).",
        "max.string" to "Количество символов в поле :attribute не может превышать :max.",
        "max.array" to "Количество элементов в поле :attribute не может превышать :max.",
        "mimes" to "Поле :attribute должно быть файлом одного из следующих типов: :values.",
        "mimetypes" to "Поле :attribute должно быть файлом одного из следующих типов: :values.",
        "min.numeric" to "Поле :attribute должно быть не менее :min.",
        "min.file" to "Размер файла в поле :attribute должен быть не менее :min Килобайт(а).",
        "min.string" to "Количество символов в поле :attribute должно быть не менее :min.",
        "min.array" to "Количество элементов в поле :attribute должно быть не менее :min.",
        "not_in" to "Выбранное значение для :attribute ошибочно.",
        "not_regex" to "Выбранный формат для :attribute ошибочный.",
        "numeric" to "Поле :attribute должно быть числом.",
        "password" to "Неверный пароль.",
        "present" to "Поле :attribute должно присутствовать.",
        "regex" to "Поле :attribute имеет ошибочный формат.",
        "required" to "Поле :attribute обязательно для заполнения.",
        "required_if" to "Поле :attribute обязательно для заполнения, когда :other равно :value.",
        "required_unless" to "Поле :attribute обязательно для заполнения, когда :other не равно :values.",
        "required_with" to "Поле :attribute обязательно для заполнения, когда :values указано.",
        "required_with_all" to "Поле :attribute обязательно для заполнения, когда :values указано.",
        "required_without" to "Поле :attribute обязательно для заполнения, когда :values не указано.",
        "required_without_all" to "Поле :attribute обязательно для заполнения, когда ни одно из :values не указано.",
        "same" to "Значения полей :attribute и :other должны совпадать.",
        "size.numeric" to "Поле :attribute должно быть равным :size.",
        "size.file" to "Размер файла в поле :attribute должен быть равен :size Килобайт(а).",
        "size.string" to "Количество символов в поле :attribute должно быть равным :size.",
        "size.array" to "Количество элементов в поле :attribute должно быть равным :size.",
        "starts_with" to "Поле :attribute должно начинаться из одного из следующих значений: :values",
        "string" to "Поле :attribute должно быть строкой.",
        "timezone" to "Поле :attribute должно быть действительным часовым поясом.",
        "unique" to "Такое значение поля :attribute уже существует.",
        "uploaded" to "Загрузка поля :attribute не удалась.",
        "url" to "Поле :attribute имеет ошибочный формат.",
        "uuid" to "Поле :attribute должно быть корректным UUID."
)